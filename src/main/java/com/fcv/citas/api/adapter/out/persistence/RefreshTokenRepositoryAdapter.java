package com.fcv.citas.api.adapter.out.persistence;

import com.fcv.citas.api.adapter.out.persistence.entity.RefreshTokenJpaEntity;
import com.fcv.citas.api.adapter.out.persistence.repository.RefreshTokenJpaRepository;
import com.fcv.citas.api.application.port.out.RefreshTokenRepositoryPort;
import com.fcv.citas.api.domain.model.RefreshToken;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepositoryPort {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    public RefreshTokenRepositoryAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenJpaEntity entity = new RefreshTokenJpaEntity(
                refreshToken.getId(),
                refreshToken.getUserId(),
                refreshToken.getTokenHash(),
                refreshToken.getExpiresAt(),
                refreshToken.getRevokedAt(),
                refreshToken.getDeviceInfo());
        RefreshTokenJpaEntity saved = refreshTokenJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        return refreshTokenJpaRepository.findByTokenHash(tokenHash).map(this::toDomain);
    }

    private RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return new RefreshToken(
                entity.getId(),
                entity.getUserId(),
                entity.getTokenHash(),
                entity.getExpiresAt(),
                entity.getRevokedAt(),
                entity.getDeviceInfo());
    }
}
