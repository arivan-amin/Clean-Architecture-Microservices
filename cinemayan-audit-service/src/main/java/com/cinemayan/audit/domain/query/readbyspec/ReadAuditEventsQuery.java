package com.cinemayan.audit.domain.query.readbyspec;

import com.cinemayan.audit.domain.persistence.AuditEventStorage;
import com.cinemayan.audit.domain.persistence.ReadAuditEventsParams;
import io.github.arivanamin.cinemayan.core.domain.audit.AuditEvent;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginatedResponse;
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
