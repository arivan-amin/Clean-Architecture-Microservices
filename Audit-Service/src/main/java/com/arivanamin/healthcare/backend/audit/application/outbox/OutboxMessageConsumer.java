package com.arivanamin.healthcare.backend.audit.application.outbox;

import com.arivanamin.healthcare.backend.audit.core.outbox.PatientCreatedEvent;
import com.arivanamin.healthcare.backend.audit.core.outbox.PatientDeletedEvent;
import com.arivanamin.healthcare.backend.base.domain.aspects.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.arivanamin.healthcare.backend.base.domain.topics.OutboxTopics.OUTBOX_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxMessageConsumer {

    @KafkaListener (topics = OUTBOX_TOPIC, groupId = "outbox-created-message-group")
    @LogExecutionTime
    public void consumePatientCreated (PatientCreatedEvent patientCreatedEvent) {
        log.info("consumed patientCreatedEvent = {}", patientCreatedEvent);
    }

    @KafkaListener (topics = OUTBOX_TOPIC, groupId = "outbox-deleted-message-group")
    @LogExecutionTime
    public void consumePatientDeleted (PatientDeletedEvent patientDeletedEvent) {
        log.info("consumed patientDeletedEvent = {}", patientDeletedEvent);
    }
}
