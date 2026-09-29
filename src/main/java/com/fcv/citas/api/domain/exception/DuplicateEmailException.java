package com.fcv.citas.api.domain.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("El email ya está registrado: " + email);
    }
}
