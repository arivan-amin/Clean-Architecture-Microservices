package io.github.arivanamin.cinemayan.audit.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.cinemayan.backend.core.domain.pagination.PaginatedResponse;
import lombok.Value;

@Value
public class ReadAuditEventsOutput {

    PaginatedResponse<AuditEvent> events;
}
