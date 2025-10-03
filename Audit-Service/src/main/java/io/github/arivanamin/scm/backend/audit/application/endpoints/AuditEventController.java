package io.github.arivanamin.scm.backend.audit.application.endpoints;

import io.github.arivanamin.scm.backend.audit.application.response.AuditEventResponse;
import io.github.arivanamin.scm.backend.audit.application.response.ReadAuditEventsResponse;
import io.github.arivanamin.scm.backend.audit.core.query.ReadAuditEventByIdQuery;
import io.github.arivanamin.scm.backend.audit.core.query.ReadAuditEventsQuery;
import io.github.arivanamin.scm.backend.base.core.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginationCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.github.arivanamin.scm.backend.audit.application.config.AuditApiURLs.GET_EVENTS_URL;
import static io.github.arivanamin.scm.backend.audit.application.config.AuditApiURLs.GET_EVENT_BY_ID_URL;

@Tag (name = "Audit Event Controller")
@RestController
@RequiredArgsConstructor
@Slf4j
class AuditEventController {

    private final ReadAuditEventsQuery readQuery;
    private final ReadAuditEventByIdQuery readByIdQuery;

    @GetMapping (GET_EVENTS_URL)
    @Operation (summary = "Get a list of auditEvents")
    @ResponseStatus (HttpStatus.OK)
    public ReadAuditEventsResponse getAllAuditEvents (@RequestParam long start,
                                                      @RequestParam long end,
                                                      @RequestParam Integer page,
                                                      @RequestParam Integer size) {
        return ReadAuditEventsResponse.of(
            readQuery.execute(DateTimeRange.of(start, end), PaginationCriteria.of(page, size)));
    }

    @GetMapping (GET_EVENT_BY_ID_URL)
    @Operation (summary = "Get a single auditEvent by id")
    @ResponseStatus (HttpStatus.OK)
    public AuditEventResponse getAuditEventById (@PathVariable UUID id) {
        return AuditEventResponse.of(readByIdQuery.execute(id));
    }
}
