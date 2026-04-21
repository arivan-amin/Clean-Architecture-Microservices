package io.github.arivanamin.cinemayan.catalog.infrastructure.repository;

import io.github.arivanamin.cinemayan.catalog.infrastructure.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository
    extends JpaRepository<StudentEntity, UUID>, JpaSpecificationExecutor<StudentEntity> {

    Optional<StudentEntity> findByEmail (String email);
}
