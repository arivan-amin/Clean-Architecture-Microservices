package io.github.arivanamin.lms.backend.patient.storage.repository;

import io.github.arivanamin.lms.backend.patient.storage.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

    Optional<PatientEntity> findByEmail (String email);
}
