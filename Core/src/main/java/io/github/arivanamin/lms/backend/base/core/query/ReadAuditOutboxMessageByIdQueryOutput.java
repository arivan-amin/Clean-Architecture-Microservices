package io.github.arivanamin.lms.backend.base.core.query;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByIdQueryOutput {

    AuditEvent event;
}
