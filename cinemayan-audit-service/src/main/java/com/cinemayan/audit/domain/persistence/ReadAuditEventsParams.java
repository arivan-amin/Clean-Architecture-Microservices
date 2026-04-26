package com.cinemayan.audit.domain.persistence;

import io.github.arivanamin.cinemayan.core.domain.pagination.PaginationCriteria;
import lombok.Value;

import java.time.Instant;

@Value
public class ReadAuditEventsParams {

    Instant startDate;
    Instant endDate;
    PaginationCriteria criteria;
}
