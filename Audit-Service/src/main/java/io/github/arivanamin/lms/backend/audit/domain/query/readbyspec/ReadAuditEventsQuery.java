package io.github.arivanamin.lms.backend.audit.domain.query.readbyspec;

import io.github.arivanamin.lms.backend.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.audit.domain.persistence.ReadAuditEventsParams;
import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {

    private final AuditEventStorage storage;

    public ReadAuditEventsQueryOutput execute (ReadAuditEventsQueryInput input) {
        ReadAuditEventsParams params =
            new ReadAuditEventsParams(input.getStartDate(), input.getEndDate(),
                input.getCriteria());

        PaginatedResponse<AuditEvent> events = storage.findAll(params);
        return new ReadAuditEventsQueryOutput(events);
    }
}
