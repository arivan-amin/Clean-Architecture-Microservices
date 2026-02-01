package io.github.arivanamin.lms.backend.student.core.query.readbyspec;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import lombok.Value;

@Value
public class ReadStudentsQueryOutput {

    PaginatedResponse<Student> students;
}
