package io.github.arivanamin.scm.backend.patient.core.command;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UpdatePatientCommandTest implements BaseUnitTest {

    private final Patient patient = Instancio.create(Patient.class);

    @Mock
    private PatientStorage storage;

    @InjectMocks
    private UpdatePatientCommand command;

    @Test
    void commandShouldCallStorageUpdate () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted();
        thenVerifyCommandCallsStorageUpdate();
    }

    private void givenCommandWithMockStorage () {
    }

    private void whenCommandIsExecuted () {
        command.execute(patient);
    }

    private void thenVerifyCommandCallsStorageUpdate () {
        verify(storage, times(1)).update(any());
    }

    @Test
    void commandShouldPassParameterToStorage () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted();
        thenAssertCommandPassesParameterToStorage();
    }

    private void thenAssertCommandPassesParameterToStorage () {
        ArgumentCaptor<Patient> patientArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        verify(storage).update(patientArgumentCaptor.capture());
        Patient result = patientArgumentCaptor.getValue();
        Assertions.assertThat(result)
            .isSameAs(patient);
    }
}
