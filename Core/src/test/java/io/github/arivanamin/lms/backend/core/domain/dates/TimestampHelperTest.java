package io.github.arivanamin.lms.backend.core.domain.dates;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.github.arivanamin.lms.backend.core.domain.dates.TimestampHelper.toLocalDate;
import static io.github.arivanamin.lms.backend.core.domain.dates.TimestampHelper.toLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TimestampHelperTest {

    @Test
    void shouldConvertLocalDateTimeToTimestamp () {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2019, 11, 15, 13, 15, 30);

        // when
        Long timestamp = TimestampHelper.toTimestampInMilliseconds(dateTime);

        // then
        assertThat(timestamp).isEqualTo(1573812930000L);
    }

    @Test
    void shouldConvertTimestampToLocalDate () {
        // given
        long timestamp = 1573841730000L;

        // when
        LocalDate localDate = toLocalDate(timestamp);

        // then
        assertThat(localDate).isEqualTo(LocalDate.parse("2019-11-15"));
    }

    @Test
    void shouldConvertTimestampToLocalDateTime () {
        // given
        long timestamp = 1573841730000L;

        // when
        LocalDateTime dateTime = toLocalDateTime(timestamp);

        // then
        assertThat(dateTime).isEqualTo(LocalDateTime.parse("2019-11-15T21:15:30"));
    }

    @Test
    void shouldGenerateSampleForTesting () {
        Long start =
            TimestampHelper.toTimestampInMilliseconds(LocalDateTime.of(1991, 1, 1, 0, 0, 0));
        Long end = TimestampHelper.toTimestampInMilliseconds(LocalDateTime.of(2027, 1, 1, 0, 0, 0));
        log.info("start = {}", start);
        log.info("end = {}", end);
    }
}
