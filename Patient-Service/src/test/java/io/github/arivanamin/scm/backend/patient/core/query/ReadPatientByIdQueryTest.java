package io.github.arivanamin.scm.backend.patient.core.query;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.exception.PatientNotFoundException;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Slf4j
class ReadPatientByIdQueryTest implements BaseUnitTest {

    private final UUID id = UUID.randomUUID();
    private final Patient patient = Instancio.create(Patient.class);

    @Mock
    private PatientStorage storage;

    @InjectMocks
    private ReadPatientByIdQuery query;

    @Test
    void queryShouldCallStorageFindById () {
        givenQueryWithMockStorage();
        whenQueryIsExecuted();
        thenVerifyQueryCallsStorageFindById();
    }

    private void givenQueryWithMockStorage () {
        when(storage.findById(id)).thenReturn(Optional.of(patient));
    }

    private Patient whenQueryIsExecuted () {
        return query.execute(id);
    }

    private void thenVerifyQueryCallsStorageFindById () {
        verify(storage, times(1)).findById(id);
    }

    @Test
    void executeShouldReturnResultFromStorageFindById () {
        givenQueryWithMockStorage();
        Patient result = whenQueryIsExecuted();
        thenVerifyFindByIdResultIsReturned(result);
    }

    private void thenVerifyFindByIdResultIsReturned (Patient result) {
        assertThat(result).isSameAs(patient);
    }

    @Test
    void queryShouldThrowExceptionWhenPatientNotFound () {
        givenQueryWithMockStorageThatThrowsException();
        thenAssertQueryThrowsPatientNotFoundException();
    }

    private void givenQueryWithMockStorageThatThrowsException () {
        when(storage.findById(id)).thenThrow(PatientNotFoundException.class);
    }

    private void thenAssertQueryThrowsPatientNotFoundException () {
        assertThrows(PatientNotFoundException.class, () -> query.execute(id));
    }
}
