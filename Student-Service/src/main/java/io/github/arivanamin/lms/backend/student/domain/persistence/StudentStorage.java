package io.github.arivanamin.lms.backend.student.domain.persistence;

import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;

import java.util.Optional;
import java.util.UUID;

public interface StudentStorage {

    PaginatedResponse<Student> findAll (PaginationCriteria criteria);

    Optional<Student> findById (UUID id);

    Optional<Student> findByEmail (String email);

    UUID create (Student student);

    void update (Student student);

    void delete (UUID id);
}
