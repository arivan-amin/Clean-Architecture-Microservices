package com.arivanamin.healthcare.backend.audit.core.persistence;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginatedResponse;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuditEventStorage {
    
    PaginatedResponse<AuditEvent> findAll (LocalDateTime start, LocalDateTime end,
                                           PaginationCriteria criteria);
    
    PaginatedResponse<AuditEvent> findAllByCriteria (AuditEvent searchCriteria,
                                                     PaginationCriteria paginationCriteria);
    
    Optional<AuditEvent> findById (String id);
    
    String create (AuditEvent event);
}
