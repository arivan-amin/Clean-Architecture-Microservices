package io.github.arivanamin.scm.backend.audit.core.util;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toLocalDateTime;

@Value
@AllArgsConstructor
public class AuditPeriod {

    LocalDateTime start;
    LocalDateTime end;

    public static AuditPeriod of (long startTimestamp, long endTimestamp) {
        return new AuditPeriod(toLocalDateTime(startTimestamp), toLocalDateTime(endTimestamp));
    }
}
