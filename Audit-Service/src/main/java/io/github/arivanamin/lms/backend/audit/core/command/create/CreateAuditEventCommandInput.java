package io.github.arivanamin.lms.backend.audit.core.command.create;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditEventCommandInput {

    AuditEvent event;
}
