package io.github.arivanamin.cinemayan.backend.core.domain.audit;

@FunctionalInterface
public interface AuditEventPublisher {

    void sendAuditLog (String topic, AuditEvent event);
}
