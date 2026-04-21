package io.github.arivanamin.cinemayan.catalog.domain.query.readbyid;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import lombok.Value;

@Value
public class ReadStudentByIdOutput {

    Student student;
}
