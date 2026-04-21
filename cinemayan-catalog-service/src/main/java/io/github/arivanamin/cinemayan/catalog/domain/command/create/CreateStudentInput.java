package io.github.arivanamin.cinemayan.catalog.domain.command.create;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import lombok.Value;

@Value
public class CreateStudentInput {

    Student student;
}
