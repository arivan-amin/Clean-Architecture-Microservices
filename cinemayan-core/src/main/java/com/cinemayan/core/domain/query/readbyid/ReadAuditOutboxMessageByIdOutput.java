package com.cinemayan.core.domain.query.readbyid;

import com.cinemayan.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditOutboxMessageByIdOutput {

    AuditEvent event;
}
