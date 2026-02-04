package io.github.arivanamin.lms.backend.student.domain.command.create;

import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.Value;

@Value
public class CreateStudentCommandInput {

    Student student;
}
