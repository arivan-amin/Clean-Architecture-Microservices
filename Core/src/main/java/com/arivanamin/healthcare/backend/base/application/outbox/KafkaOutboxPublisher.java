package com.arivanamin.healthcare.backend.base.application.outbox;

import com.arivanamin.healthcare.backend.base.domain.outbox.OutboxEvent;
import com.arivanamin.healthcare.backend.base.domain.outbox.OutboxPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
public class KafkaOutboxPublisher implements OutboxPublisher {

    private final KafkaTemplate<String, OutboxEvent> kafkaTemplate;

    @Override
    public void sendMessage (String topic, OutboxEvent request) {
        var completable = kafkaTemplate.send(topic, request);

        completable.whenComplete((result, throwable) -> {
            String formattedResult = result == null ? "" : result.toString();
            String formattedThrowable = throwable == null ? "" : throwable.getMessage();
            log.info("published outbox message with result= {}, and throwable = {}",
                formattedResult, formattedThrowable);
        });
    }
}
