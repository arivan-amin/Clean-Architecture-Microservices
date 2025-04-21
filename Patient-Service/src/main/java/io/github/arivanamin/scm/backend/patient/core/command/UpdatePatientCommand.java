package io.github.arivanamin.scm.backend.patient.core.command;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePatientCommand {

    private final PatientStorage storage;

    public void execute (Patient patient) {
        storage.update(patient);
    }
}
