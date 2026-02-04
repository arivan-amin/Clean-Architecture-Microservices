package io.github.arivanamin.lms.backend.student.domain.command.update;

import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.Value;

@Value
public class UpdateStudentCommandInput {

    Student student;
}
