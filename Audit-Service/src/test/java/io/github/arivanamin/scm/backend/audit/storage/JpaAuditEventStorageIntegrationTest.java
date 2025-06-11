package io.github.arivanamin.scm.backend.audit.storage;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.common.storage.audit.*;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseDatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

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
        start = LocalDateTime.now()
            .minusDays(2);
        end = LocalDateTime.now()
            .minusDays(1);
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

    @Test
    void findAllShouldBeInclusiveOnStartDate () {
        // given
        createAuditEventsAndSaveToStorage(eventsCount, start);
        createAuditEventsAndSaveToStorage(eventsCount, start.minusMinutes(2));

        // when
        whenFindAllIsCalled();

        // then
        thenAssertThatAllEventsWithinDateTimeRangeIsReturned();
    }

    private void createAuditEventsAndSaveToStorage (int count, LocalDateTime recordedAt) {
        for (int i = 0; i < count; i++) {
            JpaAuditEvent entity = createSampleEvent(recordedAt);
            repository.save(entity);
        }
    }

    private void whenFindAllIsCalled () {
        result = storage.findAll(dateTimeRange, PaginationCriteria.of(0, eventsCount))
            .getContent();
    }

    private void thenAssertThatAllEventsWithinDateTimeRangeIsReturned () {
        assertThat(result.size()).isEqualTo(eventsCount);
    }

    private JpaAuditEvent createSampleEvent (LocalDateTime recordedAt) {
        JpaAuditEvent entity = Instancio.create(JpaAuditEvent.class);
        entity.setId(null);
        entity.setRecordedAt(recordedAt);
        return entity;
    }

    @Test
    void findAllShouldBeInclusiveOnEndDate () {
        // given
        createAuditEventsAndSaveToStorage(eventsCount, end);
        createAuditEventsAndSaveToStorage(eventsCount, end.plusMinutes(2));

        // when
        whenFindAllIsCalled();

        // then
        thenAssertThatAllEventsWithinDateTimeRangeIsReturned();
    }

    private void createEventsWithinDateTimeRange (int numberOfSavedEvents) {
        createAuditEventsAndSaveToStorage(numberOfSavedEvents, start.plusMinutes(5));
    }

    private void createEventsOutsideOfDateTimeRange () {
        createAuditEventsAndSaveToStorage(3, start.minusMinutes(1));
        createAuditEventsAndSaveToStorage(3, end.plusMinutes(1));
    }

    @Test
    void findByIdShouldReturnCorrectEvent () {
        // given
        repository.save(createSampleEvent(LocalDateTime.now()));
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
}
