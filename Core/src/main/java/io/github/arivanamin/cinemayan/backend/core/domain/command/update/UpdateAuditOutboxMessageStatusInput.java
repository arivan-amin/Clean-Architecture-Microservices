package io.github.arivanamin.cinemayan.backend.core.domain.command.update;

import io.github.arivanamin.cinemayan.backend.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateAuditOutboxMessageStatusInput {

    UUID messageId;
    OutboxMessageStatus status;
}
