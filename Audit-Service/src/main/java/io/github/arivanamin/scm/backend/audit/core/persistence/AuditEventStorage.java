package io.github.arivanamin.scm.backend.audit.core.persistence;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.core.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginationCriteria;

import java.util.Optional;
import java.util.UUID;

public interface AuditEventStorage {

    PaginatedResponse<AuditEvent> findAll (DateTimeRange range, PaginationCriteria criteria);

    Optional<AuditEvent> findById (UUID id);

    UUID create (AuditEvent event);
}
