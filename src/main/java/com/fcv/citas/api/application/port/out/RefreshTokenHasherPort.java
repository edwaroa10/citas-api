package com.fcv.citas.api.application.port.out;

public interface RefreshTokenHasherPort {

    String hash(String rawRefreshToken);
}
