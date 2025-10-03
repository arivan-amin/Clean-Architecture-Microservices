package io.github.arivanamin.scm.backend.audit.storage;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.core.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.core.dates.TimestampHelper;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseDatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static io.github.arivanamin.scm.backend.audit.AuditEventTestingUtility.createSampleEvent;
import static io.github.arivanamin.scm.backend.audit.storage.JpaAuditEvent.fromDomain;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaAuditEventStorageIntegrationTest extends BaseDatabaseTest {

    LocalDateTime start;
    LocalDateTime end;
    DateTimeRange dateTimeRange;

    @Autowired
    private AuditEventRepository repository;
    private JpaAuditEventStorage storage;

    private int eventsCount;
    private List<AuditEvent> result;

    @BeforeEach
    void setUp () {
        storage = new JpaAuditEventStorage(repository);
        eventsCount = FAKER.number()
            .numberBetween(3, 10);
        start = LocalDateTime.of(2025, 3, 4, 10, 0, 0);
        end = LocalDateTime.of(2025, 3, 4, 11, 0, 0);
        dateTimeRange = DateTimeRange.of(start, end);
    }

    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }

    @Test
    void findAllShouldReturnAuditEventsBasedOnDateTimeRange () {
        // given
        createEventsWithinDateTimeRange(eventsCount);
        createEventsOutsideOfDateTimeRange();

        // when
        whenFindAllIsCalled();

        // then
        thenAssertThatAllEventsWithinDateTimeRangeIsReturned();
    }

    private void createEventsWithinDateTimeRange (int numberOfSavedEvents) {
        createAuditEventsAndSaveToStorage(numberOfSavedEvents, start.plusMinutes(FAKER.number()
            .numberBetween(1, 57)));
    }

    private void createEventsOutsideOfDateTimeRange () {
        createAuditEventsAndSaveToStorage(3, start.minusMinutes(3));
        createAuditEventsAndSaveToStorage(3, end.plusMinutes(3));
    }

    private void whenFindAllIsCalled () {
        result = storage.findAll(dateTimeRange, PaginationCriteria.of(0, eventsCount))
            .getContent();
    }

    private void thenAssertThatAllEventsWithinDateTimeRangeIsReturned () {
        assertThat(result.size()).isEqualTo(eventsCount);
    }

    private void createAuditEventsAndSaveToStorage (int count, LocalDateTime recordedAt) {
        for (int i = 0; i < count; i++) {
            JpaAuditEvent entity = fromDomain(createSampleEvent(recordedAt));
            repository.save(entity);
        }
    }

    @Test
    void findAllShouldBeInclusiveOnStartDate () {
        // given
        createAuditEventsAndSaveToStorage(eventsCount, start);
        createAuditEventsAndSaveToStorage(eventsCount, start.minusMinutes(3));

        // when
        whenFindAllIsCalled();

        // then
        thenAssertThatAllEventsWithinDateTimeRangeIsReturned();
    }

    @Test
    void findAllShouldBeInclusiveOnEndDate () {
        // given
        createAuditEventsAndSaveToStorage(eventsCount, end);
        createAuditEventsAndSaveToStorage(eventsCount, end.plusMinutes(3));

        // when
        whenFindAllIsCalled();

        // then
        thenAssertThatAllEventsWithinDateTimeRangeIsReturned();
    }

    @Test
    void findAllShouldSortEventsBasedOnRecordedAtDescending () {
        // given
        repository.save(fromDomain(createSampleEvent(start.plusMinutes(10))));
        repository.save(fromDomain(createSampleEvent(start.plusMinutes(5))));
        repository.save(fromDomain(createSampleEvent(start.plusMinutes(15))));

        // when
        whenFindAllIsCalled();

        // then
        assertThat(TimestampHelper.toLocalDateTime(result.get(0)
            .getTimestamp())).isEqualTo(start.plusMinutes(15));
        assertThat(TimestampHelper.toLocalDateTime(result.get(1)
            .getTimestamp())).isEqualTo(start.plusMinutes(10));
        assertThat(TimestampHelper.toLocalDateTime(result.get(2)
            .getTimestamp())).isEqualTo(start.plusMinutes(5));
    }

    @Test
    void findByIdShouldReturnCorrectEvent () {
        // given
        repository.save(fromDomain(createSampleEvent(LocalDateTime.now())));
        JpaAuditEvent savedEvent = repository.findAll()
            .stream()
            .findFirst()
            .orElseThrow();

        // when
        AuditEvent result = storage.findById(savedEvent.getId())
            .orElseThrow();

        // then
        assertThat(result).isEqualTo(savedEvent.toDomain());
    }

    @Test
    void createShouldSaveEventInRepository () {
        // given
        LocalDateTime recordedAt = LocalDateTime.of(2025, 3, 4, 7, 9, 0);
        AuditEvent event = createSampleEvent(recordedAt);

        // when
        storage.create(event);

        // then
        List<JpaAuditEvent> result = repository.findAll();
        assertThat(result.size()).isOne();
        assertThat(result.getFirst()
            .getRecordedAt()).isEqualTo(recordedAt);
    }
}
