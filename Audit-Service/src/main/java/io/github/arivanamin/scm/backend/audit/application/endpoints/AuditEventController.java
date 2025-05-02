package io.github.arivanamin.scm.backend.audit.application.endpoints;

import io.github.arivanamin.scm.backend.audit.application.request.AuditEventCriteria;
import io.github.arivanamin.scm.backend.audit.application.response.AuditEventResponse;
import io.github.arivanamin.scm.backend.audit.application.response.ReadAuditEventsResponse;
import io.github.arivanamin.scm.backend.audit.core.query.*;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.github.arivanamin.scm.backend.audit.application.config.AuditApiURLs.*;

@Tag (name = "Audit Event Controller")
@RestController
@RequiredArgsConstructor
@Slf4j
class AuditEventController {

    private final ReadAuditEventsQuery readQuery;
    private final ReadAuditEventsByCriteriaQuery readByCriteriaQuery;
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

    @GetMapping (GET_EVENT_BY_CRITERIA_URL)
    @Operation (summary = "Get a list of auditEvents by criteria")
    @ResponseStatus (HttpStatus.OK)
    public ReadAuditEventsResponse getAllAuditEventsByCriteria (
        @RequestBody @Valid AuditEventCriteria criteria, @RequestParam Integer page,
        @RequestParam Integer size) {
        return ReadAuditEventsResponse.of(
            readByCriteriaQuery.execute(criteria.toDomain(), PaginationCriteria.of(page, size)));
    }

    @GetMapping (GET_EVENT_BY_ID_URL)
    @Operation (summary = "Get a single auditEvent by id")
    @ResponseStatus (HttpStatus.OK)
    public AuditEventResponse getAuditEventById (@PathVariable UUID id) {
        return AuditEventResponse.of(readByIdQuery.execute(id));
    }
}
