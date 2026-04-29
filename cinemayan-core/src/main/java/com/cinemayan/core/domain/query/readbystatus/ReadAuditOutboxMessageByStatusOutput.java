package com.cinemayan.core.domain.query.readbystatus;

import com.cinemayan.core.domain.audit.AuditEvent;
import lombok.Value;

import java.util.List;

@Value
public class ReadAuditOutboxMessageByStatusOutput {

    List<AuditEvent> events;
}
