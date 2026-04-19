package io.github.arivanamin.cinemayan.backend.core.domain.query.readbystatus;

import io.github.arivanamin.cinemayan.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByStatusInput {

    OutboxMessageStatus status;
}
