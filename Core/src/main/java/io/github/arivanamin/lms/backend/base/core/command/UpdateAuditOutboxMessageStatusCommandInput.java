package io.github.arivanamin.lms.backend.base.core.command;

import io.github.arivanamin.lms.backend.base.core.outbox.OutboxMessageStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateAuditOutboxMessageStatusCommandInput {

    UUID messageId;
    OutboxMessageStatus status;
}
