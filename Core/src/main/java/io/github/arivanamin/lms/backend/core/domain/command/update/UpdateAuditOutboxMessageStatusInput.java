package io.github.arivanamin.lms.backend.core.domain.command.update;

import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateAuditOutboxMessageStatusInput {

    UUID messageId;
    OutboxMessageStatus status;
}
