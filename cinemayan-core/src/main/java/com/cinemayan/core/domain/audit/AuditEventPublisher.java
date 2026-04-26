package com.cinemayan.core.domain.audit;

@FunctionalInterface
public interface AuditEventPublisher {

    void sendAuditLog (String topic, AuditEvent event);
}
