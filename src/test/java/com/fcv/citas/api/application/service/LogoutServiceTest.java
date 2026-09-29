package com.fcv.citas.api.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fcv.citas.api.application.port.out.RefreshTokenHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenRepositoryPort;
import com.fcv.citas.api.domain.model.RefreshToken;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogoutServiceTest {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    @Mock
    private RefreshTokenRepositoryPort refreshTokenRepository;

    @Mock
    private RefreshTokenHasherPort refreshTokenHasher;

    private LogoutService service;

    @BeforeEach
    void setUp() {
        service = new LogoutService(refreshTokenRepository, refreshTokenHasher, Clock.fixed(NOW, ZoneOffset.UTC));
    }

    @Test
    void revokesActiveToken() {
        RefreshToken active = RefreshToken.issue(1L, "HASH-a", NOW.plus(Duration.ofDays(1)));
        when(refreshTokenHasher.hash("raw")).thenReturn("HASH-a");
        when(refreshTokenRepository.findByTokenHash("HASH-a")).thenReturn(Optional.of(active));

        service.logout("raw");

        ArgumentCaptor<RefreshToken> captor = ArgumentCaptor.forClass(RefreshToken.class);
        verify(refreshTokenRepository).save(captor.capture());
        assertThat(captor.getValue().getRevokedAt()).isEqualTo(NOW);
    }

    @Test
    void isIdempotentWhenTokenNotFound() {
        when(refreshTokenHasher.hash(anyString())).thenReturn("HASH-missing");
        when(refreshTokenRepository.findByTokenHash("HASH-missing")).thenReturn(Optional.empty());

        service.logout("missing");

        verify(refreshTokenRepository, never()).save(any());
    }

    @Test
    void isIdempotentWhenTokenAlreadyRevoked() {
        RefreshToken alreadyRevoked = RefreshToken.issue(1L, "HASH-r", NOW.plus(Duration.ofDays(1)))
                .revoke(NOW.minusSeconds(5));
        when(refreshTokenHasher.hash(anyString())).thenReturn("HASH-r");
        when(refreshTokenRepository.findByTokenHash("HASH-r")).thenReturn(Optional.of(alreadyRevoked));

        service.logout("raw-revoked");

        verify(refreshTokenRepository, never()).save(any());
    }
}
