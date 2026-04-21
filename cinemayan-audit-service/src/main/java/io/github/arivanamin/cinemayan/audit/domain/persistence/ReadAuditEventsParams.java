package io.github.arivanamin.cinemayan.audit.domain.persistence;

import io.github.arivanamin.cinemayan.backend.core.domain.pagination.PaginationCriteria;
import lombok.Value;

import java.time.Instant;

@Value
public class ReadAuditEventsParams {

    Instant startDate;
    Instant endDate;
    PaginationCriteria criteria;
}
