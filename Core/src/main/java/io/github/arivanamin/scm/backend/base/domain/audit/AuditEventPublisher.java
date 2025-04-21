package io.github.arivanamin.scm.backend.base.domain.audit;

@FunctionalInterface
public interface AuditEventPublisher {

    void sendAuditLog (String topic, AuditEvent event);
}
