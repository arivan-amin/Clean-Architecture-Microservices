package io.github.arivanamin.scm.backend.patient.core.query;

import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReadPatientsQueryTest implements BaseUnitTest {

    private final List<Patient> patients = List.of();

    private PatientStorage storage;
    private ReadPatientsQuery query;

    @Test
    void shouldCallPersistenceFindAll () {
        givenQueryWithMockStorage();
        whenQueryIsExecuted();
        thenVerifyQueryCallsFindAll();
    }

    private void givenQueryWithMockStorage () {
        storage = mock(PatientStorage.class);
        when(storage.findAll(PAGINATION_CRITERIA)).thenReturn(
            PaginatedResponse.of(PAGE_DATA, patients));
        query = new ReadPatientsQuery(storage);
    }

    private PaginatedResponse<Patient> whenQueryIsExecuted () {
        return query.execute(PAGINATION_CRITERIA);
    }

    private void thenVerifyQueryCallsFindAll () {
        verify(storage, times(1)).findAll(PAGINATION_CRITERIA);
    }

    @Test
    void shouldReturnResultOfPersistenceFindAll () {
        givenQueryWithMockStorage();
        List<Patient> result = whenQueryIsExecuted().getContent();
        thenVerifyFindAllResultIsReturned(result);
    }

    private void thenVerifyFindAllResultIsReturned (List<Patient> result) {
        assertThat(patients).isSameAs(result);
    }
}
