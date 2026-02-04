package io.github.arivanamin.lms.backend.audit.domain.query.readbyspec;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import lombok.Value;

@Value
public class ReadAuditEventsQueryOutput {

    PaginatedResponse<AuditEvent> events;
}
