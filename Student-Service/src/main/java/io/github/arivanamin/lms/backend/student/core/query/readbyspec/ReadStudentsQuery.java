package io.github.arivanamin.lms.backend.student.core.query.readbyspec;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadStudentsQuery {

    private final StudentStorage storage;

    public ReadStudentsQueryOutput execute (ReadStudentsQueryInput input) {
        PaginatedResponse<Student> students = storage.findAll(input.getCriteria());
        return new ReadStudentsQueryOutput(students);
    }
}
