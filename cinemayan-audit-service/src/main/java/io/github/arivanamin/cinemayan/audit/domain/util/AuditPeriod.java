package io.github.arivanamin.cinemayan.audit.domain.util;

import io.github.arivanamin.cinemayan.core.domain.util.MappingUtility;
import lombok.Value;

import java.time.Instant;

@Value
public class AuditPeriod {

    Instant start;
    Instant end;

    public static AuditPeriod of (long startTimestamp, long endTimestamp) {
        return new AuditPeriod(MappingUtility.mapIfNotNull(startTimestamp, Instant::ofEpochMilli),
            MappingUtility.mapIfNotNull(endTimestamp, Instant::ofEpochMilli));
    }
}
