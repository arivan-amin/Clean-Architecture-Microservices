package com.cinemayan.audit.domain.query.readbyspec;

import com.cinemayan.core.domain.audit.AuditEvent;
import com.cinemayan.core.domain.pagination.PaginatedResponse;
import lombok.Value;

@Value
public class ReadAuditEventsOutput {

    PaginatedResponse<AuditEvent> events;
}
