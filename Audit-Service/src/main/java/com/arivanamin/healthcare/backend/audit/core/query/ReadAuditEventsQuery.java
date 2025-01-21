package com.arivanamin.healthcare.backend.audit.core.query;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.audit.core.util.AuditPeriod;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginatedResponse;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {
    
    private final AuditEventStorage storage;
    
    public PaginatedResponse<AuditEvent> execute (AuditPeriod auditPeriod,
                                                  PaginationCriteria criteria) {
        return storage.findAll(auditPeriod.getStart(), auditPeriod.getEnd(), criteria);
    }
}
