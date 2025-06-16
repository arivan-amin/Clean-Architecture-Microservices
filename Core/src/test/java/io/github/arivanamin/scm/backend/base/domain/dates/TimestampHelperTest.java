package io.github.arivanamin.scm.backend.base.domain.dates;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TimestampHelperTest {

    @Test
    void toTimestampInMillisecondsShouldConvertToLocalDateTime () {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2019, 11, 15, 13, 15, 30);

        // when
        Long timestamp = TimestampHelper.toTimestampInMilliseconds(dateTime);

        // then
        assertThat(timestamp).isEqualTo(1573812930000L);
    }

    @Test
    void toLocalDateTimeShouldConvertToTimestampInMilliseconds () {
        // given
        long timestamp = 1573841730000L;

        // when
        LocalDateTime dateTime = TimestampHelper.toLocalDateTime(timestamp);

        // then
        assertThat(dateTime).isEqualTo(LocalDateTime.parse("2019-11-15T21:15:30"));
    }

    @Test
    void toTimestampInMillisecondsShouldGenerateSampleForTesting () {
        Long start =
            TimestampHelper.toTimestampInMilliseconds(LocalDateTime.of(2024, 12, 25, 0, 0, 0));
        Long end =
            TimestampHelper.toTimestampInMilliseconds(LocalDateTime.of(2024, 12, 30, 0, 0, 0));
        log.info("start = {}", start);
        log.info("end = {}", end);
    }
}
