package io.github.arivanamin.cinemayan.backend.core.domain.command.create;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateAuditOutboxMessageOutput {

    UUID id;
}
