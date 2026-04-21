package io.github.arivanamin.cinemayan.student.domain.command.delete;

import lombok.Value;

import java.util.UUID;

@Value
public class DeleteStudentInput {

    UUID id;
}
