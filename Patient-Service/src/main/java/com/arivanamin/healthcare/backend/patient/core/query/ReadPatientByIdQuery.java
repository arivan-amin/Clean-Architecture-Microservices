package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientNotFoundException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
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
