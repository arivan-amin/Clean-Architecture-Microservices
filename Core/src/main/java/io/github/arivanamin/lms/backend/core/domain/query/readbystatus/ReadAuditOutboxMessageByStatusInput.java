package io.github.arivanamin.lms.backend.core.domain.query.readbystatus;

import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByStatusInput {

    OutboxMessageStatus status;
}
