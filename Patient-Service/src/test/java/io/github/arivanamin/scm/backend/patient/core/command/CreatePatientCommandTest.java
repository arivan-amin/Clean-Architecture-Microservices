package io.github.arivanamin.scm.backend.patient.core.command;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.exception.PatientAlreadyExistsException;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.Mockito.*;

class CreatePatientCommandTest implements BaseUnitTest {

    private final UUID createdPatientId = UUID.randomUUID();
    private final String emailAddress = FAKER.internet()
        .emailAddress();

    @Mock
    private PatientStorage storage;

    @InjectMocks
    private CreatePatientCommand command;

    private Patient patient;

    @Test
    void commandShouldThrowExceptionWhenPatientExists () {
        givenCommandWithMockFindByEmail();
        whenEmailIsDuplicate();
        thenThrowPatientAlreadyExistsException();
    }

    private void givenCommandWithMockFindByEmail () {
        Patient patient = Instancio.create(Patient.class);
        patient.setEmail(emailAddress);
        when(storage.findByEmail(emailAddress)).thenReturn(Optional.of(patient));
    }

    private void whenEmailIsDuplicate () {
        patient = Instancio.create(Patient.class);
        patient.setEmail(emailAddress);
    }

    private void thenThrowPatientAlreadyExistsException () {
        assertThatException().isThrownBy(() -> command.execute(patient))
            .isInstanceOf(PatientAlreadyExistsException.class);
    }

    @Test
    void commandShouldCallStorageCreate () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted();
        thenVerifyCommandCallsCreate();
    }

    private void givenCommandWithMockStorage () {
        when(storage.create(any())).thenReturn(createdPatientId);
        patient = Instancio.create(Patient.class);
    }

    private UUID whenCommandIsExecuted () {
        return command.execute(patient);
    }

    private void thenVerifyCommandCallsCreate () {
        verify(storage, times(1)).create(patient);
    }

    @Test
    void commandShouldReturnResultOfStorageCreate () {
        givenCommandWithMockStorage();
        UUID resultId = whenCommandIsExecuted();
        thenVerifyCreateResultIsReturned(resultId);
    }

    private void thenVerifyCreateResultIsReturned (UUID resultId) {
        assertThat(resultId).isSameAs(createdPatientId);
    }
}
