package io.github.arivanamin.cam.backend.audit.application.consumer;

import io.github.arivanamin.cam.backend.audit.core.command.CreateAuditEventCommand;
import io.github.arivanamin.cam.backend.base.domain.aspects.LogExecutionTime;
import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.arivanamin.cam.backend.base.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiAuditConsumer {

    private final CreateAuditEventCommand command;

    @KafkaListener (topics = API_AUDIT_TOPIC)
    @LogExecutionTime
    public void consumeAuditEvent (AuditEvent auditEvent) {
        saveToStorage(auditEvent);
    }

    private void saveToStorage (AuditEvent event) {
        UUID savedEventId = command.execute(event);
        log.info("savedEventId = {}", savedEventId);
    }
}
