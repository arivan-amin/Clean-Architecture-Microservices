package io.github.arivanamin.lms.backend.student.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.Value;

@Value
public class ReadStudentsOutput {

    PaginatedResponse<Student> students;
}
