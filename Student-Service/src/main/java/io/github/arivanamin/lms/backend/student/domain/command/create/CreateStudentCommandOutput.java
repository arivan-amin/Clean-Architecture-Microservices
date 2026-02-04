package io.github.arivanamin.lms.backend.student.domain.command.create;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateStudentCommandOutput {

    UUID id;
}
