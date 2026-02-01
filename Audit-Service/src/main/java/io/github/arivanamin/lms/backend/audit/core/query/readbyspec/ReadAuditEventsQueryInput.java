package io.github.arivanamin.lms.backend.audit.core.query.readbyspec;

import io.github.arivanamin.lms.backend.base.core.dates.DateTimeRange;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import lombok.Value;

@Value
public class ReadAuditEventsQueryInput {

    DateTimeRange dateTimeRange;
    PaginationCriteria criteria;
}
