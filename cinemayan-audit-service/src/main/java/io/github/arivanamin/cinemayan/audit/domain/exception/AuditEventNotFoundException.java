package io.github.arivanamin.cinemayan.audit.domain.exception;

public class AuditEventNotFoundException extends RuntimeException {

    public AuditEventNotFoundException () {
        super("AuditEvent by the requested id not found");
    }
}
