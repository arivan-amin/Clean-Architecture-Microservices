package io.github.arivanamin.lms.backend.student.core.persistence;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.core.entity.Student;

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
