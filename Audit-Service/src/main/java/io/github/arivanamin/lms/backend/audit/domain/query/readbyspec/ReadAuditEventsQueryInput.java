package io.github.arivanamin.lms.backend.audit.domain.query.readbyspec;

import io.github.arivanamin.lms.backend.core.domain.dates.DateTimeRange;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;
import lombok.Value;

@Value
public class ReadAuditEventsQueryInput {

    DateTimeRange dateTimeRange;
    PaginationCriteria criteria;
}
