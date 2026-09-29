package com.fcv.citas.api.application.service;

/**
 * Señal interna cuando el adaptador de persistencia detecta, a nivel de restricción única de BD,
 * una colisión que la verificación previa en el caso de uso no alcanzó a detectar (condición de
 * carrera). El servicio de aplicación la traduce a la excepción de dominio correspondiente.
 */
public class DataIntegrityRaceException extends RuntimeException {

    private final boolean duplicateEmail;

    private DataIntegrityRaceException(boolean duplicateEmail) {
        this.duplicateEmail = duplicateEmail;
    }

    public static DataIntegrityRaceException forEmail() {
        return new DataIntegrityRaceException(true);
    }

    public static DataIntegrityRaceException forDocument() {
        return new DataIntegrityRaceException(false);
    }

    public boolean duplicateEmail() {
        return duplicateEmail;
    }
}
