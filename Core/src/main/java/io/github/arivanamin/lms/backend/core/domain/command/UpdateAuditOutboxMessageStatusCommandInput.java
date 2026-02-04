package io.github.arivanamin.lms.backend.core.domain.command;

import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateAuditOutboxMessageStatusCommandInput {

    UUID messageId;
    OutboxMessageStatus status;
}
