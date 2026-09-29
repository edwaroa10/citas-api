package com.fcv.citas.api.application.service;

import com.fcv.citas.api.application.port.in.AuthenticatedSession;
import com.fcv.citas.api.application.port.in.RefreshSessionUseCase;
import com.fcv.citas.api.application.port.out.AccessTokenIssuerPort;
import com.fcv.citas.api.application.port.out.AccessTokenIssuerPort.IssuedAccessToken;
import com.fcv.citas.api.application.port.out.RefreshTokenHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenRepositoryPort;
import com.fcv.citas.api.application.port.out.UserRepositoryPort;
import com.fcv.citas.api.domain.exception.InvalidRefreshTokenException;
import com.fcv.citas.api.domain.model.RefreshToken;
import com.fcv.citas.api.domain.model.User;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class RefreshSessionService implements RefreshSessionUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepository;
    private final RefreshTokenHasherPort refreshTokenHasher;
    private final UserRepositoryPort userRepository;
    private final AccessTokenIssuerPort accessTokenIssuer;
    private final Clock clock;
    private final long refreshDays;

    public RefreshSessionService(
            RefreshTokenRepositoryPort refreshTokenRepository,
            RefreshTokenHasherPort refreshTokenHasher,
            UserRepositoryPort userRepository,
            AccessTokenIssuerPort accessTokenIssuer,
            Clock clock,
            long refreshDays) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenHasher = refreshTokenHasher;
        this.userRepository = userRepository;
        this.accessTokenIssuer = accessTokenIssuer;
        this.clock = clock;
        this.refreshDays = refreshDays;
    }

    @Override
    public AuthenticatedSession refresh(String rawRefreshToken) {
        String tokenHash = refreshTokenHasher.hash(rawRefreshToken);
        RefreshToken currentToken = refreshTokenRepository.findByTokenHash(tokenHash)
                .filter(token -> token.isActive(clock.instant()))
                .orElseThrow(InvalidRefreshTokenException::new);

        User user = userRepository.findById(currentToken.getUserId())
                .filter(User::isActive)
                .orElseThrow(InvalidRefreshTokenException::new);

        // Rotación: el token usado queda revocado y se emite uno nuevo.
        refreshTokenRepository.save(currentToken.revoke(clock.instant()));

        IssuedAccessToken accessToken = accessTokenIssuer.issue(user.getId(), user.getEmail(), user.getRoles());

        String newRawRefreshToken = OpaqueTokenGenerator.generate();
        Instant newExpiresAt = clock.instant().plus(Duration.ofDays(refreshDays));
        refreshTokenRepository.save(
                RefreshToken.issue(user.getId(), refreshTokenHasher.hash(newRawRefreshToken), newExpiresAt));

        long expiresInSeconds = Duration.between(clock.instant(), accessToken.expiresAt()).getSeconds();
        return new AuthenticatedSession(
                accessToken.token(), newRawRefreshToken, "Bearer", expiresInSeconds, user.getRoles());
    }
}
