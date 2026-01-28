package io.github.arivanamin.lms.backend.student.core.command.create;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import lombok.Value;

@Value
public class CreateStudentCommandInput {

    Student student;
}
