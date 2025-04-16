package io.github.arivanamin.cam.backend.patient.core.command;

import io.github.arivanamin.cam.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePatientCommand {

    private final PatientStorage storage;

    public void execute (UUID id) {
        storage.delete(id);
    }
}
