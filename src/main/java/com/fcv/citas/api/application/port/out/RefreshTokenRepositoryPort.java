package com.fcv.citas.api.application.port.out;

import com.fcv.citas.api.domain.model.RefreshToken;
import java.util.Optional;

public interface RefreshTokenRepositoryPort {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);
}
