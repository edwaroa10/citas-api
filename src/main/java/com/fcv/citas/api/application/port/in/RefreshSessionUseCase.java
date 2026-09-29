package com.fcv.citas.api.application.port.in;

public interface RefreshSessionUseCase {

    AuthenticatedSession refresh(String rawRefreshToken);
}
