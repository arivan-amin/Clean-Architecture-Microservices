package com.arivanamin.healthcare.backend.audit.application.consumer;

import com.arivanamin.healthcare.backend.audit.core.command.CreateAuditEventCommand;
import com.arivanamin.healthcare.backend.base.domain.aspects.LogExecutionTime;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.arivanamin.healthcare.backend.base.domain.topics.AuditTopics.API_AUDIT_TOPIC;

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
        String savedEventId = command.execute(event);
        log.info("savedEventId = {}", savedEventId);
    }
}
