package io.github.arivanamin.cinemayan.student.domain.command.create;

import io.github.arivanamin.cinemayan.student.domain.entity.Student;
import lombok.Value;

@Value
public class CreateStudentInput {

    Student student;
}
