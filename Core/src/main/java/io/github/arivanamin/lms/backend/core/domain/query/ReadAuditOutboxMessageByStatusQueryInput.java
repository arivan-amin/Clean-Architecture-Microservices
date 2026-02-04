package io.github.arivanamin.lms.backend.core.domain.query;

import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByStatusQueryInput {

    OutboxMessageStatus status;
}
