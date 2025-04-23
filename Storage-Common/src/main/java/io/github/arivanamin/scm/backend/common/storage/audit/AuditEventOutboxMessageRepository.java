package io.github.arivanamin.scm.backend.common.storage.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditEventOutboxMessageRepository
    extends JpaRepository<JpaAuditEventOutboxMessage, UUID> {

}
