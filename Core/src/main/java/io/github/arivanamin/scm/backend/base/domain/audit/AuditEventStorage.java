package io.github.arivanamin.scm.backend.base.domain.audit;

import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;

import java.util.Optional;
import java.util.UUID;

public interface AuditEventStorage {

    PaginatedResponse<AuditEvent> findAll (DateTimeRange range, PaginationCriteria criteria);

    PaginatedResponse<AuditEvent> findAllByCriteria (AuditEvent searchCriteria,
                                                     PaginationCriteria paginationCriteria);

    Optional<AuditEvent> findById (UUID id);

    UUID create (AuditEvent event);
}
