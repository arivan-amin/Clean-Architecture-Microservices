package com.cinemayan.core.domain.command.create;

import com.cinemayan.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditOutboxMessageInput {

    AuditEvent auditEvent;
}
