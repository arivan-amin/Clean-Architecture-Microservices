package com.arivanamin.healthcare.backend.audit.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface AuditEventRepository extends MongoRepository<JpaAuditEvent, String> {
    
    Page<JpaAuditEvent> findAllByRecordedAtBetween (LocalDateTime start, LocalDateTime end,
                                                    Pageable pageable);
}
