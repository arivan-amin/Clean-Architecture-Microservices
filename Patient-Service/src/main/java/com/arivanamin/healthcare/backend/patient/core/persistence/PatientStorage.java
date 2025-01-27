package com.arivanamin.healthcare.backend.patient.core.persistence;

import com.arivanamin.healthcare.backend.base.domain.pagination.PaginatedResponse;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;

import java.util.Optional;
import java.util.UUID;

public interface PatientStorage {

    PaginatedResponse<Patient> findAll (PaginationCriteria criteria);

    Optional<Patient> findById (UUID id);

    Optional<Patient> findByEmail (String email);

    UUID create (Patient patient);

    void update (Patient patient);

    void delete (UUID id);
}
