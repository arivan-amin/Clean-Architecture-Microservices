package io.github.arivanamin.scm.backend.base.core.audit;

@FunctionalInterface
public interface AuditEventPublisher {

    void sendAuditLog (String topic, AuditEvent event);
}
