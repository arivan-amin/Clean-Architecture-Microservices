package io.github.arivanamin.lms.backend.student.core.query;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadStudentsQuery {

    private final StudentStorage storage;

    public PaginatedResponse<Student> execute (PaginationCriteria paginationCriteria) {
        return storage.findAll(paginationCriteria);
    }
}
