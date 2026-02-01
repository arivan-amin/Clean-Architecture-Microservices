package io.github.arivanamin.lms.backend.audit.core.query.readbyspec;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import lombok.Value;

@Value
public class ReadAuditEventsQueryOutput {

    PaginatedResponse<AuditEvent> events;
}
