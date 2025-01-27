package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.base.domain.pagination.PaginatedResponse;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadPatientsQuery {

    private final PatientStorage storage;

    public PaginatedResponse<Patient> execute (PaginationCriteria paginationCriteria) {
        return storage.findAll(paginationCriteria);
    }
}
