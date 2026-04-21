package io.github.arivanamin.cinemayan.catalog.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginatedResponse;
import lombok.Value;

@Value
public class ReadStudentsOutput {

    PaginatedResponse<Student> students;
}
