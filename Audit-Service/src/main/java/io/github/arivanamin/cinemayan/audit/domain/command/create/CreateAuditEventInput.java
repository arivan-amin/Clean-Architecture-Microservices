package io.github.arivanamin.cinemayan.audit.domain.command.create;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditEventInput {

    AuditEvent event;
}
