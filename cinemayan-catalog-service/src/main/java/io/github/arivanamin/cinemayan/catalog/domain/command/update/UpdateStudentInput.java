package io.github.arivanamin.cinemayan.catalog.domain.command.update;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import lombok.Value;

@Value
public class UpdateStudentInput {

    Student student;
}
