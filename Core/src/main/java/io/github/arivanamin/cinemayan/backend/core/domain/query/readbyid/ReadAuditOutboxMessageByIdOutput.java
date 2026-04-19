package io.github.arivanamin.cinemayan.backend.core.domain.query.readbyid;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByIdOutput {

    AuditEvent event;
}
