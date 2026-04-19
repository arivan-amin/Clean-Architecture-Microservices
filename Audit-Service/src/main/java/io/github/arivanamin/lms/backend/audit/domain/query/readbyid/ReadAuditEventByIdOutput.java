package io.github.arivanamin.lms.backend.audit.domain.query.readbyid;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditEventByIdOutput {

    AuditEvent event;
}
