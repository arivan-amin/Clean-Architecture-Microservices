package com.arivanamin.healthcare.backend.audit.core.query;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginatedResponse;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsByCriteriaQuery {

    private final AuditEventStorage storage;

    public PaginatedResponse<AuditEvent> execute (AuditEvent criteria,
                                                  PaginationCriteria paginationCriteria) {
        return storage.findAllByCriteria(criteria, paginationCriteria);
    }
}
