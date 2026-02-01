package io.github.arivanamin.lms.backend.student.core.command.update;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import lombok.Value;

@Value
public class UpdateStudentCommandInput {

    Student student;
}
