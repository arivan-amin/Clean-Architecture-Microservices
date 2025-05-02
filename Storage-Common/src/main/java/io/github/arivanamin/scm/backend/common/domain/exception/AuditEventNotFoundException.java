package io.github.arivanamin.scm.backend.common.domain.exception;

public class AuditEventNotFoundException extends RuntimeException {

    public AuditEventNotFoundException () {
        super("AuditEvent by the requested id not found");
    }
}
