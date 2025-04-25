package io.github.arivanamin.scm.backend.base.application.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventPublisher;
import io.github.arivanamin.scm.backend.base.domain.audit.outbox.UpdateAuditOutboxMessageStatusCommand;
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
        kafkaTemplate.send(topic, event)
            .thenAccept(result -> {
                updateCommand.execute(event.getId(), OutboxMessageStatus.SENT);
                log.info("Audit event: {} sent successfully", event.getId());
            })
            .exceptionally(throwable -> {
                log.error("Failed to send audit event: {}", event.getId(), throwable);
                return null;
            });
        /*var completable = kafkaTemplate.send(topic, event);
        completable.whenComplete((result, throwable) -> {
            String formattedResult = result == null ? "" : result.toString();
            String formattedThrowable = throwable == null ? "" : throwable.getMessage();
            log.info("published audit event with result= {}, and throwable = {}", formattedResult,
                formattedThrowable);
            if (throwable == null) {
                updateCommand.execute(event.getId(), OutboxMessageStatus.SENT);
                // log.info("Audit event: {} sent successfully", event.getId());
            }
            if (result == null) {
                // log.error("Failed to send audit event: {}", event.getId());
            }
        });*/
    }
}
