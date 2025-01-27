package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePatientCommand {

    private final PatientStorage storage;

    public void execute (UUID id) {
        storage.delete(id);
    }
}
