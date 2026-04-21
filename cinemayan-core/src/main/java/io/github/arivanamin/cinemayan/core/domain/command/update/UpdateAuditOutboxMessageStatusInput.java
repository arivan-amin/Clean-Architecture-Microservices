package io.github.arivanamin.cinemayan.core.domain.command.update;

import io.github.arivanamin.cinemayan.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateAuditOutboxMessageStatusInput {

    UUID messageId;
    OutboxMessageStatus status;
}
