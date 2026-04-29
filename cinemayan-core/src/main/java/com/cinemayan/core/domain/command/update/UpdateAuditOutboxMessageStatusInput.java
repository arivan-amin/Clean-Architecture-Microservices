package com.cinemayan.core.domain.command.update;

import com.cinemayan.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateAuditOutboxMessageStatusInput {

    UUID messageId;
    OutboxMessageStatus status;
}
