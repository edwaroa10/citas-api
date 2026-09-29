package com.fcv.citas.api.application.port.in;

public interface LogoutUseCase {

    void logout(String rawRefreshToken);
}
