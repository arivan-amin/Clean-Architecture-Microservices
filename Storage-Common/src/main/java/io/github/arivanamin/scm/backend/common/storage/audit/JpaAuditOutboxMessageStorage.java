package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.outbox.*;
import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class JpaAuditOutboxMessageStorage implements AuditOutboxMessageStorage {

    private final AuditOutboxMessageRepository repository;

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

    @Transactional
    @Override
    public void updateMessageStatus (UUID messageId, OutboxMessageStatus status) {
        JpaAuditOutboxMessage message = repository.findById(messageId)
            .orElseThrow(AuditOutboxMessageNotFound::new);
        message.setStatus(status);
        repository.save(message);
    }

    @Override
    public void deleteAllCompleted () {
        List<JpaAuditOutboxMessage> completedMessages =
            repository.findAllByStatus(OutboxMessageStatus.COMPLETED);
        repository.deleteAll(completedMessages);
    }
}
