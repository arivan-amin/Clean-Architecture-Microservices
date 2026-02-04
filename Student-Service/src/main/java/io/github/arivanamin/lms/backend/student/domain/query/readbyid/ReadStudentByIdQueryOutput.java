package io.github.arivanamin.lms.backend.student.domain.query.readbyid;

import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.Value;

@Value
public class ReadStudentByIdQueryOutput {

    Student student;
}
