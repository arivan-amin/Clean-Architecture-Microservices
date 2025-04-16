package io.github.arivanamin.cam.backend.audit.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AuditEventRepository extends JpaRepository<JpaAuditEvent, String> {

    Page<JpaAuditEvent> findAllByRecordedAtBetween (LocalDateTime start, LocalDateTime end,
                                                    Pageable pageable);
}
