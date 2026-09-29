package com.fcv.citas.api.domain.exception;

public class DuplicateDocumentException extends RuntimeException {

    public DuplicateDocumentException(String documentType, String documentNumber) {
        super("El documento ya está registrado: " + documentType + " " + documentNumber);
    }
}
