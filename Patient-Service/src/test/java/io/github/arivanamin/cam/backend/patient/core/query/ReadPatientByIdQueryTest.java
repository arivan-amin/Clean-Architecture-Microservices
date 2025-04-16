package io.github.arivanamin.cam.backend.patient.core.query;

import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import io.github.arivanamin.cam.backend.patient.core.exception.PatientNotFoundException;
import io.github.arivanamin.cam.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.cam.backend.testing.architecture.bases.BaseUnitTest;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReadPatientByIdQueryTest implements BaseUnitTest {

    private final Patient patient = Instancio.create(Patient.class);
    private final UUID id = UUID.randomUUID();

    private PatientStorage persistence;
    private ReadPatientByIdQuery query;

    @Test
    void shouldCallPersistenceFindById () {
        givenQueryWithMockPersistence();
        whenQueryIsExecuted();
        thenVerifyQueryCallsPersistenceFindById();
    }

    private void givenQueryWithMockPersistence () {
        persistence = mock(PatientStorage.class);
        when(persistence.findById(id)).thenReturn(Optional.of(patient));
        query = new ReadPatientByIdQuery(persistence);
    }

    private Patient whenQueryIsExecuted () {
        return query.execute(id);
    }

    private void thenVerifyQueryCallsPersistenceFindById () {
        verify(persistence, times(1)).findById(id);
    }

    @Test
    void shouldReturnResultFromPersistenceFindById () {
        givenQueryWithMockPersistence();
        Patient result = whenQueryIsExecuted();
        thenVerifyFindByIdResultIsReturned(result);
    }

    private void thenVerifyFindByIdResultIsReturned (Patient result) {
        assertThat(result).isSameAs(patient);
    }

    @Test
    void shouldThrowWhenPatientIsNotFound () {
        givenQueryWithMockThatThrowsException();
        thenAssertQueryThrowsPatientNotFoundException();
    }

    private void givenQueryWithMockThatThrowsException () {
        persistence = mock(PatientStorage.class);
        when(persistence.findById(id)).thenThrow(PatientNotFoundException.class);
        query = new ReadPatientByIdQuery(persistence);
    }

    private void thenAssertQueryThrowsPatientNotFoundException () {
        assertThrows(PatientNotFoundException.class, () -> query.execute(id));
    }
}
