package com.fcv.citas.api.adapter.in.web.dto;

import com.fcv.citas.api.application.port.in.AuthenticatedSession;
import java.util.Set;

public record TokenResponse(
        String accessToken, String refreshToken, String tokenType, long expiresInSeconds, Set<String> roles) {

    public static TokenResponse from(AuthenticatedSession session) {
        return new TokenResponse(
                session.accessToken(),
                session.refreshToken(),
                session.tokenType(),
                session.expiresInSeconds(),
                session.roles());
    }
}
