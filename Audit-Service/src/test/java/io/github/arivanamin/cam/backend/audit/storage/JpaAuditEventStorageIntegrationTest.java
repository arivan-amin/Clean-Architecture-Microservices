package io.github.arivanamin.cam.backend.audit.storage;

import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.cam.backend.testing.architecture.bases.BaseDatabaseTest;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaAuditEventStorageIntegrationTest implements BaseDatabaseTest {

    @Autowired
    private AuditEventRepository repository;

    private JpaAuditEventStorage persistence;

    private int numberOfSavedEntities;
    private UUID expectedId;

    private List<AuditEvent> expectedAuditEvents;
    private AuditEvent expectedAuditEvent;

    @BeforeEach
    void setUp () {
        persistence = new JpaAuditEventStorage(repository);
    }

    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }

    @Test
    void shouldReturnAllAuditEventsWhenFindAllIsCalled () {
        givenRepositoryWithSavedAuditEvents();
        whenFindAllIsCalled();
        thenAssertThatAllEntitiesOfRepositoryAreReturned(expectedAuditEvents);
    }

    private void givenRepositoryWithSavedAuditEvents () {
        numberOfSavedEntities = FAKER.number()
            .numberBetween(3, 10);
        for (int i = 0; i < numberOfSavedEntities; i++) {
            JpaAuditEvent entity = createSampleAuditEvent();
            repository.save(entity);
        }
    }

    private void whenFindAllIsCalled () {
        expectedAuditEvents = persistence.findAll(LocalDateTime.now()
                .minusDays(1), LocalDateTime.now(), PAGINATION_CRITERIA)
            .getContent();
    }

    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<AuditEvent> result) {
        assertThat(result.size()).isEqualTo(numberOfSavedEntities);
    }

    private static JpaAuditEvent createSampleAuditEvent () {
        JpaAuditEvent entity = Instancio.create(JpaAuditEvent.class);
        entity.setId(null);
        entity.setRecordedAt(LocalDate.now()
            .atStartOfDay()
            .minusHours(3));
        return entity;
    }
}
