package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.base.domain.notification.NotificationPublisher;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.Mockito.*;

class CreatePatientCommandTest implements BaseUnitTest {

    private final String emailAddress = "echo@mail.com";
    private final UUID createdPatientId = UUID.randomUUID();

    private PatientStorage persistence;
    private NotificationPublisher publisher;
    private CreatePatientCommand command;

    private Patient patient;

    @Test
    void shouldThrowExceptionWhenPatientExists () {
        givenCommandWithMockFindByEmail();
        whenEmailIsDuplicate();
        thenThrowPatientAlreadyExistsException();
    }

    private void givenCommandWithMockFindByEmail () {
        persistence = mock(PatientStorage.class);
        publisher = mock(NotificationPublisher.class);
        command = new CreatePatientCommand(persistence, publisher);
        Patient patient = RANDOM.nextObject(Patient.class);
        patient.setEmail(emailAddress);
        when(persistence.findByEmail(emailAddress)).thenReturn(Optional.of(patient));
    }

    private void whenEmailIsDuplicate () {
        patient = RANDOM.nextObject(Patient.class);
        patient.setEmail(emailAddress);
    }

    private void thenThrowPatientAlreadyExistsException () {
        assertThatException().isThrownBy(() -> command.execute(patient))
            .isInstanceOf(PatientAlreadyExistsException.class);
    }

    @Test
    void shouldCallPersistenceCreate () {
        givenCommandWithMockPersistence();
        whenCommandIsExecuted();
        thenVerifyCommandCallsCreate();
    }

    private void givenCommandWithMockPersistence () {
        persistence = mock(PatientStorage.class);
        publisher = mock(NotificationPublisher.class);
        command = new CreatePatientCommand(persistence, publisher);
        when(persistence.create(any())).thenReturn(createdPatientId);
        patient = RANDOM.nextObject(Patient.class);
    }

    private UUID whenCommandIsExecuted () {
        return command.execute(patient);
    }

    private void thenVerifyCommandCallsCreate () {
        verify(persistence, times(1)).create(patient);
    }

    @Test
    void shouldReturnResultOfPersistenceCreate () {
        givenCommandWithMockPersistence();
        UUID resultId = whenCommandIsExecuted();
        thenVerifyCreateResultIsReturned(resultId);
    }

    private void thenVerifyCreateResultIsReturned (UUID resultId) {
        assertThat(resultId).isSameAs(createdPatientId);
    }
}
