package io.github.arivanamin.scm.backend.audit.core.query;

import io.github.arivanamin.scm.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.core.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginationCriteria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {

    private final AuditEventStorage storage;

    public PaginatedResponse<AuditEvent> execute (DateTimeRange dateTimeRange,
                                                  PaginationCriteria criteria) {
        return storage.findAll(dateTimeRange, criteria);
    }
}
