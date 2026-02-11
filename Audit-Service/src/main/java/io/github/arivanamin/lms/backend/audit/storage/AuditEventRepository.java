package io.github.arivanamin.lms.backend.audit.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.UUID;

public interface AuditEventRepository extends JpaRepository<JpaAuditEvent, UUID> {

    Page<JpaAuditEvent> findAllByRecordedAtBetween (Instant start, Instant end, Pageable pageable);
}
