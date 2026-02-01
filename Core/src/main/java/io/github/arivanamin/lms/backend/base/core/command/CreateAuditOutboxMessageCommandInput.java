package io.github.arivanamin.lms.backend.base.core.command;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditOutboxMessageCommandInput {

    AuditEvent auditEvent;
}
