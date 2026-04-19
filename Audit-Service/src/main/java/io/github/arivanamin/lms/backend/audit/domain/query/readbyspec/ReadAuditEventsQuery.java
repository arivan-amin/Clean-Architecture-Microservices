package io.github.arivanamin.lms.backend.audit.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.cinemayan.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.audit.domain.persistence.ReadAuditEventsParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {

    private final AuditEventStorage storage;

    public ReadAuditEventsOutput execute (ReadAuditEventsInput input) {
        ReadAuditEventsParams params =
            new ReadAuditEventsParams(input.getStartDate(), input.getEndDate(),
                input.getCriteria());

        PaginatedResponse<AuditEvent> events = storage.findAll(params);
        return new ReadAuditEventsOutput(events);
    }
}
