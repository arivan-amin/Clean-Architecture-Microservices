package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DeletePatientCommandTest implements BaseUnitTest {

    private final UUID id = UUID.randomUUID();

    private PatientStorage persistence;
    private DeletePatientCommand command;

    @Test
    void shouldCallPersistenceDelete () {
        givenCommandWithMockPersistence();
        whenCommandIsExecuted(id);
        thenVerifyCommandCallsDelete();
    }

    private void givenCommandWithMockPersistence () {
        persistence = mock(PatientStorage.class);
        command = new DeletePatientCommand(persistence);
    }

    private void whenCommandIsExecuted (UUID id) {
        command.execute(id);
    }

    private void thenVerifyCommandCallsDelete () {
        verify(persistence, times(1)).delete(id);
    }

    @Test
    void shouldSendIdToPersistence () {
        givenCommandWithMockPersistence();
        whenCommandIsExecuted(id);
        thenVerifyIdIsSentToPersistence();
    }

    private void thenVerifyIdIsSentToPersistence () {
        ArgumentCaptor<UUID> idCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(persistence).delete(idCaptor.capture());

        UUID capturedId = idCaptor.getValue();
        assertThat(id).isSameAs(capturedId);
    }
}
