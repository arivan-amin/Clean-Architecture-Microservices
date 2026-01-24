package io.github.arivanamin.lms.backend.patient.core.command;

import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import io.github.arivanamin.lms.backend.patient.core.exception.PatientAlreadyExistsException;
import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreatePatientCommand {

    private final PatientStorage storage;

    public UUID execute (Patient patient) {
        if (doesPatientExist(patient)) {
            throw new PatientAlreadyExistsException();
        }
        return storage.create(patient);
    }

    private boolean doesPatientExist (Patient patient) {
        return storage.findByEmail(patient.getEmail())
            .isPresent();
    }
}
