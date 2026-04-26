package com.cinemayan.core.domain.query.readbystatus;

import com.cinemayan.core.domain.outbox.OutboxMessageStatus;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByStatusInput {

    OutboxMessageStatus status;
}
