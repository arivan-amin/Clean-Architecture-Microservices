package io.github.arivanamin.cam.backend.patient.core.exception;

public class PatientAlreadyExistsException extends RuntimeException {

    public PatientAlreadyExistsException () {
        super("patient with the requested email already exists");
    }
}
