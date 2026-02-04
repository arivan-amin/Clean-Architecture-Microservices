package io.github.arivanamin.lms.backend.audit.domain.persistence;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.dates.DateTimeRange;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;

import java.util.Optional;
import java.util.UUID;

public interface AuditEventStorage {

    PaginatedResponse<AuditEvent> findAll (DateTimeRange range, PaginationCriteria criteria);

    Optional<AuditEvent> findById (UUID id);

    UUID create (AuditEvent event);
}
