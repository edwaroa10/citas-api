package com.fcv.citas.api.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fcv.citas.api.application.port.in.AuthenticatedSession;
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
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RefreshSessionServiceTest {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");
    private static final long REFRESH_DAYS = 7;
    private static final Long USER_ID = 7L;

    @Mock
    private RefreshTokenRepositoryPort refreshTokenRepository;

    @Mock
    private RefreshTokenHasherPort refreshTokenHasher;

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private AccessTokenIssuerPort accessTokenIssuer;

    private RefreshSessionService service;

    private static final User ACTIVE_USER = new User(
            USER_ID, "Ana", "Pérez", "CC", "1000200030", "ana.perez@example.com", "3001234567", "HASHED", true,
            Set.of("USER", "ADMIN"));

    @BeforeEach
    void setUp() {
        Clock clock = Clock.fixed(NOW, ZoneOffset.UTC);
        service = new RefreshSessionService(
                refreshTokenRepository, refreshTokenHasher, userRepository, accessTokenIssuer, clock, REFRESH_DAYS);
    }

    @Test
    void rotatesRefreshTokenAndIssuesNewAccessToken() {
        String rawOldToken = "old-raw-token";
        String oldHash = "HASH-" + rawOldToken;
        RefreshToken existing = RefreshToken.issue(USER_ID, oldHash, NOW.plus(Duration.ofDays(3)));

        when(refreshTokenHasher.hash(anyString())).thenAnswer(inv -> "HASH-" + inv.getArgument(0));
        when(refreshTokenRepository.findByTokenHash(oldHash)).thenReturn(Optional.of(existing));
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(ACTIVE_USER));
        when(accessTokenIssuer.issue(USER_ID, ACTIVE_USER.getEmail(), ACTIVE_USER.getRoles()))
                .thenReturn(new IssuedAccessToken("new-access-token", NOW.plus(Duration.ofMinutes(15))));
        when(refreshTokenRepository.save(any(RefreshToken.class))).thenAnswer(inv -> inv.getArgument(0));

        AuthenticatedSession session = service.refresh(rawOldToken);

        assertThat(session.accessToken()).isEqualTo("new-access-token");
        assertThat(session.refreshToken()).isNotEqualTo(rawOldToken);
        assertThat(session.roles()).containsExactlyInAnyOrder("USER", "ADMIN");

        ArgumentCaptor<RefreshToken> captor = ArgumentCaptor.forClass(RefreshToken.class);
        verify(refreshTokenRepository, org.mockito.Mockito.times(2)).save(captor.capture());
        List<RefreshToken> saved = captor.getAllValues();

        RefreshToken revokedOld = saved.get(0);
        assertThat(revokedOld.getTokenHash()).isEqualTo(oldHash);
        assertThat(revokedOld.getRevokedAt()).isEqualTo(NOW);

        RefreshToken issuedNew = saved.get(1);
        assertThat(issuedNew.getTokenHash()).isEqualTo("HASH-" + session.refreshToken());
        assertThat(issuedNew.getRevokedAt()).isNull();
        assertThat(issuedNew.getExpiresAt()).isEqualTo(NOW.plus(Duration.ofDays(REFRESH_DAYS)));
    }

    @Test
    void rejectsUnknownToken() {
        when(refreshTokenHasher.hash(anyString())).thenReturn("HASH-x");
        when(refreshTokenRepository.findByTokenHash("HASH-x")).thenReturn(Optional.empty());

        assertThrows(InvalidRefreshTokenException.class, () -> service.refresh("does-not-exist"));

        verify(refreshTokenRepository, never()).save(any());
    }

    @Test
    void rejectsExpiredToken() {
        RefreshToken expired = RefreshToken.issue(USER_ID, "HASH-expired", NOW.minusSeconds(1));
        when(refreshTokenHasher.hash(anyString())).thenReturn("HASH-expired");
        when(refreshTokenRepository.findByTokenHash("HASH-expired")).thenReturn(Optional.of(expired));

        assertThrows(InvalidRefreshTokenException.class, () -> service.refresh("expired-raw"));

        verify(refreshTokenRepository, never()).save(any());
    }

    @Test
    void rejectsRevokedToken() {
        RefreshToken revoked = RefreshToken.issue(USER_ID, "HASH-revoked", NOW.plus(Duration.ofDays(1)))
                .revoke(NOW.minusSeconds(10));
        when(refreshTokenHasher.hash(anyString())).thenReturn("HASH-revoked");
        when(refreshTokenRepository.findByTokenHash("HASH-revoked")).thenReturn(Optional.of(revoked));

        assertThrows(InvalidRefreshTokenException.class, () -> service.refresh("revoked-raw"));

        verify(refreshTokenRepository, never()).save(any());
    }

    @Test
    void rejectsWhenUserNoLongerExistsOrIsInactive() {
        RefreshToken active = RefreshToken.issue(USER_ID, "HASH-ok", NOW.plus(Duration.ofDays(1)));
        when(refreshTokenHasher.hash(anyString())).thenReturn("HASH-ok");
        when(refreshTokenRepository.findByTokenHash("HASH-ok")).thenReturn(Optional.of(active));
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(InvalidRefreshTokenException.class, () -> service.refresh("ok-raw"));

        verify(refreshTokenRepository, never()).save(any());
    }
}
