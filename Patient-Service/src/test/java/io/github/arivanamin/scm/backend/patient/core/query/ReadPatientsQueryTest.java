package io.github.arivanamin.scm.backend.patient.core.query;

import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
class ReadPatientsQueryTest implements BaseUnitTest {

    private final List<Patient> patients = List.of();

    @Mock
    private PatientStorage storage;

    @InjectMocks
    private ReadPatientsQuery query;

    @Test
    void queryShouldCallStorageFindAll () {
        givenQueryWithMockStorage();
        whenQueryIsExecuted();
        thenVerifyQueryCallsFindAll();
    }

    private void givenQueryWithMockStorage () {
        when(storage.findAll(PAGINATION_CRITERIA)).thenReturn(
            PaginatedResponse.of(PAGE_DATA, patients));
    }

    private PaginatedResponse<Patient> whenQueryIsExecuted () {
        return query.execute(PAGINATION_CRITERIA);
    }

    private void thenVerifyQueryCallsFindAll () {
        verify(storage, times(1)).findAll(PAGINATION_CRITERIA);
    }

    @Test
    void queryShouldReturnResultOfStorageFindAll () {
        givenQueryWithMockStorage();
        List<Patient> result = whenQueryIsExecuted().getContent();
        thenVerifyFindAllResultIsReturned(result);
    }

    private void thenVerifyFindAllResultIsReturned (List<Patient> result) {
        assertThat(patients).isSameAs(result);
    }
}
