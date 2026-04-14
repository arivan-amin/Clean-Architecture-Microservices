package io.github.arivanamin.lms.backend.core.domain.query.readbyid;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByIdOutput {

    AuditEvent event;
}
