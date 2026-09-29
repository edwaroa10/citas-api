package com.fcv.citas.api.adapter.out.security;

import com.fcv.citas.api.application.port.out.AccessTokenIssuerPort;
import com.fcv.citas.api.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JjwtAccessTokenIssuerAdapter implements AccessTokenIssuerPort {

    private final SecretKey key;
    private final long accessMinutes;
    private final Clock clock;

    public JjwtAccessTokenIssuerAdapter(JwtProperties jwtProperties, Clock clock) {
        this.key = Keys.hmacShaKeyFor(jwtProperties.accessSecret().getBytes(StandardCharsets.UTF_8));
        this.accessMinutes = jwtProperties.accessMinutes();
        this.clock = clock;
    }

    @Override
    public IssuedAccessToken issue(Long userId, String email, Set<String> roles) {
        Instant now = clock.instant();
        Instant expiresAt = now.plus(Duration.ofMinutes(accessMinutes));

        String token = Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email", email)
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiresAt))
                .signWith(key)
                .compact();

        return new IssuedAccessToken(token, expiresAt);
    }
}
