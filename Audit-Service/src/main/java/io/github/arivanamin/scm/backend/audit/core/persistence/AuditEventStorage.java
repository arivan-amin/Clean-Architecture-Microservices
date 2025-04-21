package io.github.arivanamin.scm.backend.audit.core.persistence;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AuditEventStorage {

    PaginatedResponse<AuditEvent> findAll (LocalDateTime start, LocalDateTime end,
                                           PaginationCriteria criteria);

    PaginatedResponse<AuditEvent> findAllByCriteria (AuditEvent searchCriteria,
                                                     PaginationCriteria paginationCriteria);

    Optional<AuditEvent> findById (String id);

    UUID create (AuditEvent event);
}
