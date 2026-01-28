package io.github.arivanamin.lms.backend.student.core.query.readbyid;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadStudentByIdQueryInput {

    UUID id;
}
