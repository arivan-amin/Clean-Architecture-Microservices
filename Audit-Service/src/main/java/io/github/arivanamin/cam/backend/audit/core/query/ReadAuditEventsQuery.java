package io.github.arivanamin.cam.backend.audit.core.query;

import io.github.arivanamin.cam.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.cam.backend.audit.core.util.AuditPeriod;
import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.cam.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.cam.backend.base.domain.pagination.PaginationCriteria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {

    private final AuditEventStorage storage;

    public PaginatedResponse<AuditEvent> execute (AuditPeriod auditPeriod,
                                                  PaginationCriteria criteria) {
        return storage.findAll(auditPeriod.getStart(), auditPeriod.getEnd(), criteria);
    }
}
