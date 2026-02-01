package io.github.arivanamin.lms.backend.audit.core.command.create;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateAuditEventCommandOutput {

    UUID id;
}
