package com.cinemayan.audit.domain.persistence;

import com.cinemayan.core.domain.audit.AuditEvent;
import com.cinemayan.core.domain.pagination.PaginatedResponse;

import java.util.Optional;
import java.util.UUID;

public interface AuditEventStorage {

    PaginatedResponse<AuditEvent> findAll (ReadAuditEventsParams params);

    Optional<AuditEvent> findById (UUID id);

    UUID create (AuditEvent event);
}
