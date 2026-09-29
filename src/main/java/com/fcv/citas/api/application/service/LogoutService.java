package com.fcv.citas.api.application.service;

import com.fcv.citas.api.application.port.in.LogoutUseCase;
import com.fcv.citas.api.application.port.out.RefreshTokenHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenRepositoryPort;
import java.time.Clock;

public class LogoutService implements LogoutUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepository;
    private final RefreshTokenHasherPort refreshTokenHasher;
    private final Clock clock;

    public LogoutService(
            RefreshTokenRepositoryPort refreshTokenRepository,
            RefreshTokenHasherPort refreshTokenHasher,
            Clock clock) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenHasher = refreshTokenHasher;
        this.clock = clock;
    }

    @Override
    public void logout(String rawRefreshToken) {
        String tokenHash = refreshTokenHasher.hash(rawRefreshToken);
        refreshTokenRepository.findByTokenHash(tokenHash)
                .filter(token -> token.getRevokedAt() == null)
                .ifPresent(token -> refreshTokenRepository.save(token.revoke(clock.instant())));
    }
}
