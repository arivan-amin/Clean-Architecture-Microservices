package io.github.arivanamin.lms.backend.audit.domain.query.readbyid;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditEventByIdQueryOutput {

    AuditEvent event;
}
