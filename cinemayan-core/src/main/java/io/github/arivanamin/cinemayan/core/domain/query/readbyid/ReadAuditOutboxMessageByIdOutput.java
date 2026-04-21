package io.github.arivanamin.cinemayan.core.domain.query.readbyid;

import io.github.arivanamin.cinemayan.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByIdOutput {

    AuditEvent event;
}
