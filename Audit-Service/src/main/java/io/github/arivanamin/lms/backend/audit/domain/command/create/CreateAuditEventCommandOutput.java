package io.github.arivanamin.lms.backend.audit.domain.command.create;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateAuditEventCommandOutput {

    UUID id;
}
