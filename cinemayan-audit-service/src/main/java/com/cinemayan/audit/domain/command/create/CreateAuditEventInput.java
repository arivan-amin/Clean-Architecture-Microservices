package com.cinemayan.audit.domain.command.create;

import com.cinemayan.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class CreateAuditEventInput {

    AuditEvent event;
}
