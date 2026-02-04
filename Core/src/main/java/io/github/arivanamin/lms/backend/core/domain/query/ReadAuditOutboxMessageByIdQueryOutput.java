package io.github.arivanamin.lms.backend.core.domain.query;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByIdQueryOutput {

    AuditEvent event;
}
