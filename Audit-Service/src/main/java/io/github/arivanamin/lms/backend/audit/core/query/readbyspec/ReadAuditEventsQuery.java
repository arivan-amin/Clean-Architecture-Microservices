package io.github.arivanamin.lms.backend.audit.core.query.readbyspec;

import io.github.arivanamin.lms.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {

    private final AuditEventStorage storage;

    public ReadAuditEventsQueryOutput execute (ReadAuditEventsQueryInput input) {
        PaginatedResponse<AuditEvent> events =
            storage.findAll(input.getDateTimeRange(), input.getCriteria());
        return new ReadAuditEventsQueryOutput(events);
    }
}
