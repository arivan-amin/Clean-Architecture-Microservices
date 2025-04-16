package io.github.arivanamin.cam.backend.patient.core.command;

import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import io.github.arivanamin.cam.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePatientCommand {

    private final PatientStorage storage;

    public void execute (Patient patient) {
        storage.update(patient);
    }
}
