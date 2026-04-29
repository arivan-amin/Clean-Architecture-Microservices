package com.cinemayan.audit.application.response;

import com.cinemayan.core.domain.audit.AuditEvent;
import com.cinemayan.core.domain.pagination.PageData;
import com.cinemayan.core.domain.pagination.PaginatedResponse;

import java.util.List;

public record ReadAuditEventsResponse(PageData pageData, List<AuditEventResponse> events) {

    public static ReadAuditEventsResponse of (PaginatedResponse<AuditEvent> paginatedResponse) {
        return new ReadAuditEventsResponse(paginatedResponse.pageData(), paginatedResponse.content()
            .stream()
            .map(AuditEventResponse::of)
            .toList());
    }
}
