package io.github.arivanamin.cam.backend.patient.core.query;

import io.github.arivanamin.cam.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.cam.backend.base.domain.pagination.PaginationCriteria;
import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import io.github.arivanamin.cam.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadPatientsQuery {

    private final PatientStorage storage;

    public PaginatedResponse<Patient> execute (PaginationCriteria paginationCriteria) {
        return storage.findAll(paginationCriteria);
    }
}
