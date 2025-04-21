package io.github.arivanamin.scm.backend.audit.application.response;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.pagination.PageData;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import lombok.Value;

import java.util.List;

@Value
public class ReadAuditEventsResponse {

    PageData pageData;
    List<AuditEventResponse> events;

    public static ReadAuditEventsResponse of (PaginatedResponse<AuditEvent> paginatedResponse) {
        return new ReadAuditEventsResponse(paginatedResponse.getPageData(),
            paginatedResponse.getContent()
                .stream()
                .map(AuditEventResponse::of)
                .toList());
    }
}
