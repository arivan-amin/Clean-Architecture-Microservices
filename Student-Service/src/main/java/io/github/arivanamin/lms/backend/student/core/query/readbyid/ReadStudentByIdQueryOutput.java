package io.github.arivanamin.lms.backend.student.core.query.readbyid;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import lombok.Value;

@Value
public class ReadStudentByIdQueryOutput {

    Student student;
}
