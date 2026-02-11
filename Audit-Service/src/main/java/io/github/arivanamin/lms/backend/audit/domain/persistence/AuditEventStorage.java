package io.github.arivanamin.lms.backend.audit.domain.persistence;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;

import java.util.Optional;
import java.util.UUID;

public interface AuditEventStorage {

    PaginatedResponse<AuditEvent> findAll (ReadAuditEventsParams params);

    Optional<AuditEvent> findById (UUID id);

    UUID create (AuditEvent event);
}
