package io.github.arivanamin.cam.backend.base.domain.audit;

@FunctionalInterface
public interface AuditEventPublisher {

    void sendAuditLog (String topic, AuditEvent event);
}
