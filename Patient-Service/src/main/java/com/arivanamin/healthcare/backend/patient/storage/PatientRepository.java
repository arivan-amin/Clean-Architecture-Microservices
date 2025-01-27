package com.arivanamin.healthcare.backend.patient.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<JpaPatient, UUID> {

    Optional<JpaPatient> findByEmail (String email);
}
