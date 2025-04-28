package io.github.arivanamin.scm.backend.audit.application.consumer;

import io.github.arivanamin.scm.backend.audit.core.command.CreateAuditEventCommand;
import io.github.arivanamin.scm.backend.base.domain.aspects.LogExecutionTime;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.outbox.DeleteCompletedAuditOutboxMessagesCommand;
import io.github.arivanamin.scm.backend.base.domain.audit.outbox.UpdateAuditOutboxMessageStatusCommand;
import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.arivanamin.scm.backend.base.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiAuditConsumer {

    private final CreateAuditEventCommand createCommand;
    private final UpdateAuditOutboxMessageStatusCommand updateCommand;
    private final DeleteCompletedAuditOutboxMessagesCommand deleteCommand;

    @KafkaListener (topics = API_AUDIT_TOPIC)
    @LogExecutionTime
    public void consumeAuditEvent (AuditEvent auditEvent) {
        saveToStorage(auditEvent);
        updateMessageStatusToCompleted(auditEvent.getId());
        deleteCompletedMessages();
    }

    private void saveToStorage (AuditEvent event) {
        try {
            createCommand.execute(event);
            log.info("saved auditEvent to storage = {}", event.getId());
        }
        catch (Exception e) {
            log.error("Error saving auditEvent to audit database", e);
        }
    }

    private void updateMessageStatusToCompleted (UUID eventId) {
        updateCommand.execute(eventId, OutboxMessageStatus.COMPLETED);
    }

    private void deleteCompletedMessages () {
        deleteCommand.execute();
    }
}
