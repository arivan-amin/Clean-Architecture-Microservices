package com.cinemayan.core.application.audit;

import com.cinemayan.core.domain.audit.AuditEvent;
import com.cinemayan.core.domain.audit.AuditEventPublisher;
import com.cinemayan.core.domain.command.update.UpdateAuditOutboxMessageStatusCommand;
import com.cinemayan.core.domain.command.update.UpdateAuditOutboxMessageStatusInput;
import com.cinemayan.core.domain.outbox.OutboxMessageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
public class KafkaAuditEventPublisher implements AuditEventPublisher {

    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;
    private final UpdateAuditOutboxMessageStatusCommand updateCommand;

    @Override
    public void sendAuditLog (String topic, AuditEvent event) {
        log.info("Kafka attempting to send audit event from outbox table = {}", event);
        kafkaTemplate.send(topic, event)
            .thenAccept(result -> {
                updateCommand.execute(new UpdateAuditOutboxMessageStatusInput(event.getId(),
                    OutboxMessageStatus.SENT));
                log.info("Audit event: {} sent successfully", event.getId());
            })
            .exceptionally(throwable -> {
                log.error("Failed to send audit event: {}", event, throwable);
                return null;
            });
    }
}
