package com.fcv.citas.api.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fcv.citas.api.application.port.in.RegisterUserUseCase.RegisterUserCommand;
import com.fcv.citas.api.application.port.out.PasswordHasherPort;
import com.fcv.citas.api.application.port.out.UserRepositoryPort;
import com.fcv.citas.api.domain.exception.DuplicateDocumentException;
import com.fcv.citas.api.domain.exception.DuplicateEmailException;
import com.fcv.citas.api.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private PasswordHasherPort passwordHasher;

    private RegisterUserService service;

    private static final RegisterUserCommand COMMAND = new RegisterUserCommand(
            "Ana", "Pérez", "CC", "1000200030", "ana.perez@example.com", "3001234567", "Secreta123*");

    @BeforeEach
    void setUp() {
        service = new RegisterUserService(userRepository, passwordHasher);
    }

    @Test
    void registersUserWithHashedPasswordAndUserRole() {
        when(userRepository.existsByEmail(COMMAND.email())).thenReturn(false);
        when(userRepository.existsByDocument(COMMAND.documentType(), COMMAND.documentNumber())).thenReturn(false);
        when(passwordHasher.hash(COMMAND.rawPassword())).thenReturn("HASHED");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User toSave = invocation.getArgument(0);
            return toSave.withId(42L);
        });

        User result = service.register(COMMAND);

        assertThat(result.getId()).isEqualTo(42L);
        assertThat(result.getEmail()).isEqualTo(COMMAND.email());
        assertThat(result.getPasswordHash()).isEqualTo("HASHED");
        assertThat(result.getRoles()).containsExactly("USER");

        verify(userRepository).save(argThatEmailAndDocumentMatch());
    }

    @Test
    void rejectsDuplicateEmail() {
        when(userRepository.existsByEmail(COMMAND.email())).thenReturn(true);

        assertThrows(DuplicateEmailException.class, () -> service.register(COMMAND));

        verify(userRepository, never()).save(any());
        verify(passwordHasher, never()).hash(anyString());
    }

    @Test
    void rejectsDuplicateDocument() {
        when(userRepository.existsByEmail(COMMAND.email())).thenReturn(false);
        when(userRepository.existsByDocument(COMMAND.documentType(), COMMAND.documentNumber())).thenReturn(true);

        assertThrows(DuplicateDocumentException.class, () -> service.register(COMMAND));

        verify(userRepository, never()).save(any());
    }

    @Test
    void translatesRaceConditionOnEmailIntoDuplicateEmailException() {
        when(userRepository.existsByEmail(COMMAND.email())).thenReturn(false);
        when(userRepository.existsByDocument(COMMAND.documentType(), COMMAND.documentNumber())).thenReturn(false);
        when(passwordHasher.hash(COMMAND.rawPassword())).thenReturn("HASHED");
        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityRaceException.forEmail());

        assertThrows(DuplicateEmailException.class, () -> service.register(COMMAND));
    }

    @Test
    void translatesRaceConditionOnDocumentIntoDuplicateDocumentException() {
        when(userRepository.existsByEmail(COMMAND.email())).thenReturn(false);
        when(userRepository.existsByDocument(COMMAND.documentType(), COMMAND.documentNumber())).thenReturn(false);
        when(passwordHasher.hash(COMMAND.rawPassword())).thenReturn("HASHED");
        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityRaceException.forDocument());

        assertThrows(DuplicateDocumentException.class, () -> service.register(COMMAND));
    }

    private static User argThatEmailAndDocumentMatch() {
        return org.mockito.ArgumentMatchers.argThat(user ->
                user.getEmail().equals(COMMAND.email())
                        && user.getDocumentNumber().equals(COMMAND.documentNumber())
                        && user.getPasswordHash().equals("HASHED")
                        && user.isActive());
    }
}
