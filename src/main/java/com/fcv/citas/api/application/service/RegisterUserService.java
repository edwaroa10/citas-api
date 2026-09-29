package com.fcv.citas.api.application.service;

import com.fcv.citas.api.application.port.in.RegisterUserUseCase;
import com.fcv.citas.api.application.port.out.PasswordHasherPort;
import com.fcv.citas.api.application.port.out.UserRepositoryPort;
import com.fcv.citas.api.domain.exception.DuplicateDocumentException;
import com.fcv.citas.api.domain.exception.DuplicateEmailException;
import com.fcv.citas.api.domain.model.User;

public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;

    public RegisterUserService(UserRepositoryPort userRepository, PasswordHasherPort passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User register(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new DuplicateEmailException(command.email());
        }
        if (userRepository.existsByDocument(command.documentType(), command.documentNumber())) {
            throw new DuplicateDocumentException(command.documentType(), command.documentNumber());
        }

        String passwordHash = passwordHasher.hash(command.rawPassword());
        User newUser = User.newRegistration(
                command.firstName(),
                command.lastName(),
                command.documentType(),
                command.documentNumber(),
                command.email(),
                command.phone(),
                passwordHash);

        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityRaceException race) {
            throw race.duplicateEmail()
                    ? new DuplicateEmailException(command.email())
                    : new DuplicateDocumentException(command.documentType(), command.documentNumber());
        }
    }
}
