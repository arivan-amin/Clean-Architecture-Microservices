package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Slf4j
class JpaAuditEventStorageTest implements BaseUnitTest {

    @Mock
    AuditEventRepository repository;

    @InjectMocks
    JpaAuditEventStorage storage;

    LocalDateTime start = LocalDateTime.now()
        .minusDays(1);
    LocalDateTime end = LocalDateTime.now()
        .minusHours(1);

    DateTimeRange dateTimeRange = DateTimeRange.of(start, end);

    List<JpaAuditEvent> events;

    @BeforeEach
    void setUp () {
        events = new ArrayList<>();
        int numberOfEntities = FAKER.number()
            .numberBetween(5, 15);
        for (int i = 0; i < numberOfEntities; i++) {
            events.add(Instancio.create(JpaAuditEvent.class));
        }
    }

    @Test
    void findAllShouldReturnResultOfRepository () {
        // given
        when(repository.findAllByRecordedAtBetween(start, end, PAGE_REQUEST)).thenReturn(
            new PageImpl(events));

        // when
        PaginatedResponse<AuditEvent> response =
            storage.findAll(dateTimeRange, PAGINATION_CRITERIA);

        // then
        assertThat(response.getContent()
            .size()).isSameAs(events.size());
    }
}
