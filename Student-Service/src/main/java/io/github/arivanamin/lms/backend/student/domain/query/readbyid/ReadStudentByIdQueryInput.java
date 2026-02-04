package io.github.arivanamin.lms.backend.student.domain.query.readbyid;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadStudentByIdQueryInput {

    UUID id;
}
