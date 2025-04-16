package io.github.arivanamin.cam.backend.patient.core.command;

import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import io.github.arivanamin.cam.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.cam.backend.testing.architecture.bases.BaseUnitTest;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UpdatePatientCommandTest implements BaseUnitTest {

    private final Patient patient = Instancio.create(Patient.class);

    private PatientStorage persistence;
    private UpdatePatientCommand command;

    @Test
    void shouldCallPersistenceUpdate () {
        givenCommandWithMockPersistence();
        whenCommandIsExecuted();
        thenVerifyCommandCallsPersistenceUpdate();
    }

    private void givenCommandWithMockPersistence () {
        persistence = Mockito.mock(PatientStorage.class);
        command = new UpdatePatientCommand(persistence);
    }

    private void whenCommandIsExecuted () {
        command.execute(patient);
    }

    private void thenVerifyCommandCallsPersistenceUpdate () {
        verify(persistence, times(1)).update(any());
    }

    @Test
    void shouldPassParameterToPersistence () {
        givenCommandWithMockPersistence();
        whenCommandIsExecuted();
        thenAssertCommandPassesParameterToPersistence();
    }

    private void thenAssertCommandPassesParameterToPersistence () {
        ArgumentCaptor<Patient> captor = ArgumentCaptor.forClass(Patient.class);
        verify(persistence).update(captor.capture());
        Patient result = captor.getValue();
        Assertions.assertThat(result)
            .isSameAs(patient);
    }
}
