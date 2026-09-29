package com.fcv.citas.api.application.port.in;

public interface LoginUseCase {

    AuthenticatedSession login(LoginCommand command);

    record LoginCommand(String email, String rawPassword) {
    }
}
