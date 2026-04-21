package io.github.arivanamin.cinemayan.student.domain.query.readbyid;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadStudentByIdInput {

    UUID id;
}
