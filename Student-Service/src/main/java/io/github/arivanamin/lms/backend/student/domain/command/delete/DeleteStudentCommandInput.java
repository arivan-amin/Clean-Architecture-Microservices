package io.github.arivanamin.lms.backend.student.domain.command.delete;

import lombok.Value;

import java.util.UUID;

@Value
public class DeleteStudentCommandInput {

    UUID id;
}
