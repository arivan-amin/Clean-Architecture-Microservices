package com.arivanamin.healthcare.backend.base.application.audit;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
public class KafkaAuditEventPublisher implements AuditEventPublisher {

    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;

    @Override
    public void sendAuditLog (String topic, AuditEvent event) {
        var completable = kafkaTemplate.send(topic, event);

        completable.whenComplete((result, throwable) -> {
            String formattedResult = result == null ? "" : result.toString();
            String formattedThrowable = throwable == null ? "" : throwable.getMessage();
            log.info("published audit event with result= {}, and throwable = {}", formattedResult,
                formattedThrowable);
        });
    }
}
