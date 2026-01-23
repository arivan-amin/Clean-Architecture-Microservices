package io.github.arivanamin.lms.backend.audit.application.response;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.lms.backend.base.core.pagination.PageData;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;

import java.util.List;

public record ReadAuditEventsResponse(PageData pageData, List<AuditEventResponse> events) {

    public static ReadAuditEventsResponse of (PaginatedResponse<AuditEvent> paginatedResponse) {
        return new ReadAuditEventsResponse(paginatedResponse.pageData(), paginatedResponse.content()
            .stream()
            .map(AuditEventResponse::of)
            .toList());
    }
}
