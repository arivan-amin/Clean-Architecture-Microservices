package io.github.arivanamin.lms.backend.patient.core.query;

import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import io.github.arivanamin.lms.backend.patient.core.exception.PatientNotFoundException;
import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ReadPatientByIdQuery {

    private final PatientStorage storage;

    public Patient execute (UUID id) {
        return storage.findById(id)
            .orElseThrow(PatientNotFoundException::new);
    }
}
