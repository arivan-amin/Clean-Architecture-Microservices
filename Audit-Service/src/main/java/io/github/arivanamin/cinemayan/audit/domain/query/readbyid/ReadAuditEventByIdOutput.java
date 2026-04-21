package io.github.arivanamin.cinemayan.audit.domain.query.readbyid;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditEventByIdOutput {

    AuditEvent event;
}
