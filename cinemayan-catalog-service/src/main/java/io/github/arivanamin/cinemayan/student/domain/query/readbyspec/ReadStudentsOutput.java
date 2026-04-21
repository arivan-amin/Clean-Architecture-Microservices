package io.github.arivanamin.cinemayan.student.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.cinemayan.student.domain.entity.Student;
import lombok.Value;

@Value
public class ReadStudentsOutput {

    PaginatedResponse<Student> students;
}
