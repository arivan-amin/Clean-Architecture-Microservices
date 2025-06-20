package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.common.domain.exception.AuditEventNotFoundException;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.*;

import static io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditEvent.fromDomain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.PageRequest.of;

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
        Sort sort = Sort.by(Sort.Direction.DESC, "recordedAt");
        PageRequest pageRequest =
            of(PAGINATION_CRITERIA.getPage(), PAGINATION_CRITERIA.getSize(), sort);
        when(repository.findAllByRecordedAtBetween(start, end, pageRequest)).thenReturn(
            new PageImpl(events));

        // when
        PaginatedResponse<AuditEvent> response =
            storage.findAll(dateTimeRange, PAGINATION_CRITERIA);

        // then
        assertThat(response.getContent()
            .size()).isSameAs(events.size());
    }

    @Test
    void findByIdShouldThrowExceptionWhenEventNotFound () {
        // given
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenThrow(AuditEventNotFoundException.class);

        // when & then
        assertThatThrownBy(() -> storage.findById(id)).isInstanceOf(
            AuditEventNotFoundException.class);
    }

    @Test
    void findByIdShouldReturnAuditEventFromRepository () {
        // given
        UUID id = UUID.randomUUID();
        JpaAuditEvent event = Instancio.create(JpaAuditEvent.class);
        when(repository.findById(id)).thenReturn(Optional.of(event));

        // when
        AuditEvent result = storage.findById(id)
            .orElseThrow();

        // then
        assertThat(result).isEqualTo(event.toDomain());
    }

    @Test
    void createShouldPassAuditEventToRepository () {
        // given
        AuditEvent event = Instancio.create(AuditEvent.class);
        when(repository.save(any())).thenReturn(fromDomain(event));

        // when & then
        storage.create(event);
        verify(repository, times(1)).save(any());
    }

    @Test
    void createShouldReturnIdOfCreatedRecord () {
        // given
        AuditEvent eventToBeCreated = Instancio.create(AuditEvent.class);
        when(repository.save(any())).thenReturn(fromDomain(eventToBeCreated));

        // when
        UUID resultId = storage.create(eventToBeCreated);

        // then
        assertThat(resultId).isEqualTo(eventToBeCreated.getId());
    }
}
