package io.github.arivanamin.lms.backend.patient.core.command;

import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePatientCommand {

    private final PatientStorage storage;

    public void execute (UUID id) {
        storage.delete(id);
    }
}
