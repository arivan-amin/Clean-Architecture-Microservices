package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuditOutboxMessageRepository extends JpaRepository<JpaAuditOutboxMessage, UUID> {

    List<JpaAuditOutboxMessage> findAllByStatus (OutboxMessageStatus status);
}
