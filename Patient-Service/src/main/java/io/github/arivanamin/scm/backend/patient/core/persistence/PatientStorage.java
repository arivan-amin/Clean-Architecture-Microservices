package io.github.arivanamin.scm.backend.patient.core.persistence;

import io.github.arivanamin.scm.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;

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
