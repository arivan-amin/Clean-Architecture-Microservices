package io.github.arivanamin.scm.backend.base.application.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventPublisher;
import io.github.arivanamin.scm.backend.base.domain.command.UpdateAuditOutboxMessageStatusCommand;
import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
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
        log.info("Kafka received audit event to be sent = {}", event);
        kafkaTemplate.send(topic, event)
            .thenAccept(result -> {
                updateCommand.execute(event.getId(), OutboxMessageStatus.SENT);
                log.info("Audit event: {} sent successfully", event.getId());
            })
            .exceptionally(throwable -> {
                log.error("Failed to send audit event: {}", event, throwable);
                return null;
            });
    }
}
