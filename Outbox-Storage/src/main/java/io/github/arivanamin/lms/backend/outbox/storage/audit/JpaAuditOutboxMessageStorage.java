package io.github.arivanamin.lms.backend.outbox.storage.audit;

import io.github.arivanamin.lms.backend.base.core.outbox.*;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class JpaAuditOutboxMessageStorage implements AuditOutboxMessageStorage {

    private final AuditOutboxMessageRepository repository;

    @Lock (LockModeType.PESSIMISTIC_WRITE)
    @Override
    public List<AuditOutboxMessage> findAllByStatus (OutboxMessageStatus status) {
        return repository.findAllByStatus(status)
            .stream()
            .map(JpaAuditOutboxMessage::toDomain)
            .toList();
    }

    @Override
    public Optional<AuditOutboxMessage> findById (UUID id) {
        return repository.findById(id)
            .map(JpaAuditOutboxMessage::toDomain);
    }

    @Transactional
    @Override
    public UUID create (AuditOutboxMessage event) {
        return repository.save(JpaAuditOutboxMessage.fromDomain(event))
            .getId();
    }

    @Lock (LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    @Override
    public void updateMessageStatus (UUID messageId, OutboxMessageStatus status) {
        JpaAuditOutboxMessage message = repository.findById(messageId)
            .orElseThrow(AuditOutboxMessageNotFound::new);
        message.setStatus(status);
        repository.save(message);
    }

    @Lock (LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    @Override
    public void deleteAllCompleted () {
        List<JpaAuditOutboxMessage> completedMessages =
            repository.findAllByStatus(OutboxMessageStatus.COMPLETED);
        repository.deleteAll(completedMessages);
    }
}
