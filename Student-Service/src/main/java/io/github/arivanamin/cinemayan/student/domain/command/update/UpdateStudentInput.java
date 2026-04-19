package io.github.arivanamin.cinemayan.student.domain.command.update;

import io.github.arivanamin.cinemayan.student.domain.entity.Student;
import lombok.Value;

@Value
public class UpdateStudentInput {

    Student student;
}
