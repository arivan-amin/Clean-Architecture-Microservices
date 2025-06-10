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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaAuditEventStorageIntegrationTest extends BaseDatabaseTest {

    LocalDateTime start = LocalDateTime.now()
        .minusDays(1);
    LocalDateTime end = LocalDateTime.now()
        .minusHours(1);
    DateTimeRange dateTimeRange = DateTimeRange.of(start, end);

    @Autowired
    private AuditEventRepository repository;
    private JpaAuditEventStorage storage;

    private int numberOfSavedEvents;
    private UUID expectedId;
    private List<AuditEvent> expectedEvents;
    private AuditEvent expectedEvent;

    @BeforeEach
    void setUp () {
        storage = new JpaAuditEventStorage(repository);
    }

    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }

    @Test
    void findAllShouldReturnAllAuditEvents () {
        givenRepositoryWithSavedAuditEvents();
        whenFindAllIsCalled();
        thenAssertThatAllEntitiesOfRepositoryAreReturned(expectedEvents);
    }

    private void givenRepositoryWithSavedAuditEvents () {
        numberOfSavedEvents = FAKER.number()
            .numberBetween(3, 10);
        for (int i = 0; i < numberOfSavedEvents; i++) {
            JpaAuditEvent entity = createSampleEvent();
            repository.save(entity);
        }
    }

    private void whenFindAllIsCalled () {
        expectedEvents =
            storage.findAll(dateTimeRange, PaginationCriteria.of(0, numberOfSavedEvents))
                .getContent();
    }

    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<AuditEvent> result) {
        assertThat(result.size()).isEqualTo(numberOfSavedEvents);
    }

    private JpaAuditEvent createSampleEvent () {
        JpaAuditEvent entity = Instancio.create(JpaAuditEvent.class);
        entity.setId(null);
        entity.setRecordedAt(LocalDateTime.now());
        return entity;
    }
}
