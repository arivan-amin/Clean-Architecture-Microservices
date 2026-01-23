package io.github.arivanamin.lms.backend.base.core.dates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static io.github.arivanamin.lms.backend.base.core.config.CoreApplicationConfig.DEFAULT_ZONE_ID;
import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor (access = PRIVATE)
public enum TimestampHelper {
    ;

    public static LocalDateTime toLocalDateTime (long timestamp) {
        return ofInstant(ofEpochMilli(timestamp), DEFAULT_ZONE_ID);
    }

    public static Long toTimestampInMilliseconds (LocalDateTime dateTime) {
        return dateTime.atZone(DEFAULT_ZONE_ID)
            .toInstant()
            .toEpochMilli();
    }
}
