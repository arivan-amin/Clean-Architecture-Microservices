package io.github.arivanamin.lms.backend.audit.domain.command.create;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditEventCommandInput {

    AuditEvent event;
}
