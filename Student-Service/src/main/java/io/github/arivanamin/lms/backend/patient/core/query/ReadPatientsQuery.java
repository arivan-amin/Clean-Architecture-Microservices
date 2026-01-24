package io.github.arivanamin.lms.backend.patient.core.query;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadPatientsQuery {

    private final PatientStorage storage;

    public PaginatedResponse<Patient> execute (PaginationCriteria paginationCriteria) {
        return storage.findAll(paginationCriteria);
    }
}
