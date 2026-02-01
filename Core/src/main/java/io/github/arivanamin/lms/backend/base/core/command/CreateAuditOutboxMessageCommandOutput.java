package io.github.arivanamin.lms.backend.base.core.command;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateAuditOutboxMessageCommandOutput {

    UUID id;
}
