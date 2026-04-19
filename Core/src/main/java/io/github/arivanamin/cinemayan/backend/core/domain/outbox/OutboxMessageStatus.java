package io.github.arivanamin.cinemayan.backend.core.domain.outbox;

public enum OutboxMessageStatus {
    PENDING,
    SENT,
    COMPLETED,
}
