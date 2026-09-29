package com.fcv.citas.api.application.port.in;

import java.util.Set;

public record AuthenticatedSession(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresInSeconds,
        Set<String> roles) {
}
