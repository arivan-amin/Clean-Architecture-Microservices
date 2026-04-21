package io.github.arivanamin.cinemayan.audit.domain.command.create;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateAuditEventOutput {

    UUID id;
}
