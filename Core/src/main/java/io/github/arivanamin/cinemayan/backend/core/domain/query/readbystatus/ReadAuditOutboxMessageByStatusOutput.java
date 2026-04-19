package io.github.arivanamin.cinemayan.backend.core.domain.query.readbystatus;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import lombok.Value;

import java.util.List;

@Value
public class ReadAuditOutboxMessageByStatusOutput {

    List<AuditEvent> events;
}
