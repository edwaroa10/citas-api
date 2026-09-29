package com.fcv.citas.api.domain.model;

import java.time.Instant;

public final class RefreshToken {

    private final Long id;
    private final Long userId;
    private final String tokenHash;
    private final Instant expiresAt;
    private final Instant revokedAt;
    private final String deviceInfo;

    public RefreshToken(Long id, Long userId, String tokenHash, Instant expiresAt, Instant revokedAt,
            String deviceInfo) {
        this.id = id;
        this.userId = userId;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.revokedAt = revokedAt;
        this.deviceInfo = deviceInfo;
    }

    public static RefreshToken issue(Long userId, String tokenHash, Instant expiresAt) {
        return new RefreshToken(null, userId, tokenHash, expiresAt, null, null);
    }

    public RefreshToken revoke(Instant revokedAt) {
        return new RefreshToken(id, userId, tokenHash, expiresAt, revokedAt, deviceInfo);
    }

    public boolean isActive(Instant now) {
        return revokedAt == null && expiresAt.isAfter(now);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getRevokedAt() {
        return revokedAt;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }
}
