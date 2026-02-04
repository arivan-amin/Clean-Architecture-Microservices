package io.github.arivanamin.lms.backend.core.domain.command;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditOutboxMessageCommandInput {

    AuditEvent auditEvent;
}
