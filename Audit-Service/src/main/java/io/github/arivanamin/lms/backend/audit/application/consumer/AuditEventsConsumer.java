package io.github.arivanamin.lms.backend.audit.application.consumer;

import io.github.arivanamin.lms.backend.audit.domain.command.create.CreateAuditEventCommand;
import io.github.arivanamin.lms.backend.audit.domain.command.create.CreateAuditEventCommandInput;
import io.github.arivanamin.lms.backend.core.domain.aspects.LogExecutionTime;
import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.command.UpdateAuditOutboxMessageStatusCommand;
import io.github.arivanamin.lms.backend.core.domain.command.UpdateAuditOutboxMessageStatusCommandInput;
import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.arivanamin.lms.backend.core.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditEventsConsumer {

    private final CreateAuditEventCommand createCommand;
    private final UpdateAuditOutboxMessageStatusCommand updateCommand;

    @KafkaListener (topics = API_AUDIT_TOPIC)
    @LogExecutionTime
    public void consumeAuditEvent (AuditEvent auditEvent) {
        log.info("consumeAuditEvent called");
        saveToStorage(auditEvent);
        updateMessageStatusToCompleted(auditEvent.getId());
    }

    private void saveToStorage (AuditEvent event) {
        try {
            createCommand.execute(new CreateAuditEventCommandInput(event));
            log.info("saved auditEvent to storage = {}", event.getId());
        }
        catch (Exception e) {
            log.error("Error saving auditEvent to audit database", e);
        }
    }

    private void updateMessageStatusToCompleted (UUID eventId) {
        updateCommand.execute(
            new UpdateAuditOutboxMessageStatusCommandInput(eventId, OutboxMessageStatus.COMPLETED));
    }
}
