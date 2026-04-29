package com.cinemayan.audit.domain.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AuditEventNotFoundException extends AuditException {

    private final UUID eventId;

    public AuditEventNotFoundException (UUID eventId) {
        super("AuditEvent with ID: %s not found".formatted(eventId),
            AuditErrorCode.AUDIT_EVENT_NOT_FOUND);
        this.eventId = eventId;
    }

    public AuditEventNotFoundException (UUID eventId, Throwable cause) {
        super("AuditEvent with ID: %s not found".formatted(eventId), cause,
            AuditErrorCode.AUDIT_EVENT_NOT_FOUND);
        this.eventId = eventId;
    }
}
