package io.github.arivanamin.lms.backend.audit.core.query.readbyid;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditEventByIdQueryOutput {

    AuditEvent event;
}
