package io.github.arivanamin.lms.backend.student.domain.query.readbyspec;

import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.student.domain.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadStudentsQuery {

    private final StudentStorage storage;

    public ReadStudentsQueryOutput execute (ReadStudentsQueryInput input) {
        PaginatedResponse<Student> students = storage.findAll(input.getCriteria());
        return new ReadStudentsQueryOutput(students);
    }
}
