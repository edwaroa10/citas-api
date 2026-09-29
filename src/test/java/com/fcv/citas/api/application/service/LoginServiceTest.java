package com.fcv.citas.api.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fcv.citas.api.application.port.in.AuthenticatedSession;
import com.fcv.citas.api.application.port.in.LoginUseCase.LoginCommand;
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
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");
    private static final long REFRESH_DAYS = 7;

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private PasswordHasherPort passwordHasher;

    @Mock
    private AccessTokenIssuerPort accessTokenIssuer;

    @Mock
    private RefreshTokenRepositoryPort refreshTokenRepository;

    @Mock
    private RefreshTokenHasherPort refreshTokenHasher;

    private LoginService service;

    private static final User ACTIVE_USER = new User(
            7L, "Ana", "Pérez", "CC", "1000200030", "ana.perez@example.com", "3001234567", "HASHED", true,
            Set.of("USER"));

    @BeforeEach
    void setUp() {
        Clock clock = Clock.fixed(NOW, ZoneOffset.UTC);
        service = new LoginService(
                userRepository, passwordHasher, accessTokenIssuer, refreshTokenRepository, refreshTokenHasher, clock,
                REFRESH_DAYS);
    }

    @Test
    void issuesAccessAndRefreshTokensOnValidCredentials() {
        when(userRepository.findByEmail(ACTIVE_USER.getEmail())).thenReturn(Optional.of(ACTIVE_USER));
        when(passwordHasher.matches("Secreta123*", "HASHED")).thenReturn(true);
        when(accessTokenIssuer.issue(ACTIVE_USER.getId(), ACTIVE_USER.getEmail(), ACTIVE_USER.getRoles()))
                .thenReturn(new IssuedAccessToken("access-token", NOW.plus(Duration.ofMinutes(15))));
        when(refreshTokenHasher.hash(anyString())).thenAnswer(inv -> "HASH-" + inv.getArgument(0));

        AuthenticatedSession session = service.login(new LoginCommand(ACTIVE_USER.getEmail(), "Secreta123*"));

        assertThat(session.accessToken()).isEqualTo("access-token");
        assertThat(session.tokenType()).isEqualTo("Bearer");
        assertThat(session.expiresInSeconds()).isEqualTo(15 * 60);
        assertThat(session.roles()).containsExactly("USER");
        assertThat(session.refreshToken()).isNotBlank();

        ArgumentCaptor<RefreshToken> captor = ArgumentCaptor.forClass(RefreshToken.class);
        verify(refreshTokenRepository).save(captor.capture());
        RefreshToken saved = captor.getValue();
        assertThat(saved.getUserId()).isEqualTo(ACTIVE_USER.getId());
        assertThat(saved.getTokenHash()).isEqualTo("HASH-" + session.refreshToken());
        assertThat(saved.getExpiresAt()).isEqualTo(NOW.plus(Duration.ofDays(REFRESH_DAYS)));
        assertThat(saved.getRevokedAt()).isNull();
    }

    @Test
    void rejectsUnknownEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class,
                () -> service.login(new LoginCommand("nadie@example.com", "cualquiera")));

        verify(passwordHasher, never()).matches(anyString(), anyString());
        verify(refreshTokenRepository, never()).save(any());
    }

    @Test
    void rejectsInactiveUser() {
        User inactive = new User(
                8L, "Ana", "Pérez", "CC", "1000200031", "inactivo@example.com", "300", "HASHED", false,
                Set.of("USER"));
        when(userRepository.findByEmail(inactive.getEmail())).thenReturn(Optional.of(inactive));

        assertThrows(InvalidCredentialsException.class,
                () -> service.login(new LoginCommand(inactive.getEmail(), "cualquiera")));

        verify(passwordHasher, never()).matches(anyString(), anyString());
    }

    @Test
    void rejectsWrongPassword() {
        when(userRepository.findByEmail(ACTIVE_USER.getEmail())).thenReturn(Optional.of(ACTIVE_USER));
        when(passwordHasher.matches(eq("incorrecta"), eq("HASHED"))).thenReturn(false);

        assertThrows(InvalidCredentialsException.class,
                () -> service.login(new LoginCommand(ACTIVE_USER.getEmail(), "incorrecta")));

        verify(refreshTokenRepository, never()).save(any());
        verify(accessTokenIssuer, never()).issue(any(), anyString(), any());
    }
}
