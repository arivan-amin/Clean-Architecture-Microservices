package io.github.arivanamin.scm.backend.base.domain.outbox;

public class AuditOutboxMessageNotFound extends RuntimeException {

    public AuditOutboxMessageNotFound () {
        super("Audit Outbox Message by the requested id not found");
    }
}
