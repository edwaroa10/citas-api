package com.fcv.citas.api.application.service;

import com.fcv.citas.api.application.port.in.AuthenticatedSession;
import com.fcv.citas.api.application.port.in.LoginUseCase;
import com.fcv.citas.api.application.port.out.AccessTokenIssuerPort;
import com.fcv.citas.api.application.port.out.AccessTokenIssuerPort.IssuedAccessToken;
import com.fcv.citas.api.application.port.out.PasswordHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenRepositoryPort;
import com.fcv.citas.api.application.port.out.UserRepositoryPort;
import com.fcv.citas.api.domain.exception.InvalidCredentialsException;
import com.fcv.citas.api.domain.model.RefreshToken;
import com.fcv.citas.api.domain.model.User;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class LoginService implements LoginUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;
    private final AccessTokenIssuerPort accessTokenIssuer;
    private final RefreshTokenRepositoryPort refreshTokenRepository;
    private final RefreshTokenHasherPort refreshTokenHasher;
    private final Clock clock;
    private final long refreshDays;

    public LoginService(
            UserRepositoryPort userRepository,
            PasswordHasherPort passwordHasher,
            AccessTokenIssuerPort accessTokenIssuer,
            RefreshTokenRepositoryPort refreshTokenRepository,
            RefreshTokenHasherPort refreshTokenHasher,
            Clock clock,
            long refreshDays) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.accessTokenIssuer = accessTokenIssuer;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenHasher = refreshTokenHasher;
        this.clock = clock;
        this.refreshDays = refreshDays;
    }

    @Override
    public AuthenticatedSession login(LoginCommand command) {
        User user = userRepository.findByEmail(command.email())
                .filter(User::isActive)
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordHasher.matches(command.rawPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        IssuedAccessToken accessToken = accessTokenIssuer.issue(user.getId(), user.getEmail(), user.getRoles());

        String rawRefreshToken = OpaqueTokenGenerator.generate();
        Instant expiresAt = clock.instant().plus(Duration.ofDays(refreshDays));
        refreshTokenRepository.save(
                RefreshToken.issue(user.getId(), refreshTokenHasher.hash(rawRefreshToken), expiresAt));

        long expiresInSeconds = Duration.between(clock.instant(), accessToken.expiresAt()).getSeconds();
        return new AuthenticatedSession(
                accessToken.token(), rawRefreshToken, "Bearer", expiresInSeconds, user.getRoles());
    }
}
