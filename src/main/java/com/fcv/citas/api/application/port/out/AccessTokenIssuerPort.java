package com.fcv.citas.api.application.port.out;

import java.time.Instant;
import java.util.Set;

public interface AccessTokenIssuerPort {

    IssuedAccessToken issue(Long userId, String email, Set<String> roles);

    record IssuedAccessToken(String token, Instant expiresAt) {
    }
}
