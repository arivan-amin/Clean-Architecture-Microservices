package com.arivanamin.healthcare.backend.base.domain.outbox;

@FunctionalInterface
public interface OutboxPublisher {

    void sendMessage (String topic, OutboxEvent event);
}
