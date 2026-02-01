package io.github.arivanamin.lms.backend.base.core.query;

import io.github.arivanamin.lms.backend.base.core.outbox.OutboxMessageStatus;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByStatusQueryInput {

    OutboxMessageStatus status;
}
