package io.github.arivanamin.cinemayan.catalog.domain.persistence;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginationCriteria;

import java.util.Optional;
import java.util.UUID;

public interface StudentStorage {

    PaginatedResponse<Student> findAll (ReadStudentsParams params, PaginationCriteria criteria);

    Optional<Student> findById (UUID id);

    Optional<Student> findByEmail (String email);

    UUID create (Student student);

    void update (Student student);

    void delete (UUID id);
}
