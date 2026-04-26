package com.cinemayan.audit.domain.query.readbyid;

import com.cinemayan.core.domain.audit.AuditEvent;
import lombok.Value;

@Value
public class ReadAuditEventByIdOutput {

    AuditEvent event;
}
