package io.github.arivanamin.lms.backend.patient.core.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException () {
        super("Patient by the requested id not found");
    }
}
