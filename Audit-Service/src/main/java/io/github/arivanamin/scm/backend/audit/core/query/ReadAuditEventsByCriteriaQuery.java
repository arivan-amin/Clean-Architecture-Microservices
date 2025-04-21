package io.github.arivanamin.scm.backend.audit.core.query;

import io.github.arivanamin.scm.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventsByCriteriaQuery {

    private final AuditEventStorage storage;

    public PaginatedResponse<AuditEvent> execute (AuditEvent criteria,
                                                  PaginationCriteria paginationCriteria) {
        return storage.findAllByCriteria(criteria, paginationCriteria);
    }
}
