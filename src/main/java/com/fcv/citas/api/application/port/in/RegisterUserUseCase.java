package com.fcv.citas.api.application.port.in;

import com.fcv.citas.api.domain.model.User;

public interface RegisterUserUseCase {

    User register(RegisterUserCommand command);

    record RegisterUserCommand(
            String firstName,
            String lastName,
            String documentType,
            String documentNumber,
            String email,
            String phone,
            String rawPassword) {
    }
}
