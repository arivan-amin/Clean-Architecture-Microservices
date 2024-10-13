package com.arivanamin.healthcare.backend.patient.core.exception;

public class PatientNotFoundException extends RuntimeException {
    
    public PatientNotFoundException () {
        super("patient by the requested id not found");
    }
}
