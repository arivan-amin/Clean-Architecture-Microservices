package io.github.arivanamin.lms.backend.core.domain.command;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateAuditOutboxMessageCommandOutput {

    UUID id;
}
