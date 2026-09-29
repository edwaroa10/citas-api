package com.fcv.citas.api.domain.exception;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException() {
        super("El refresh token es inválido, expiró o ya fue revocado");
    }
}
