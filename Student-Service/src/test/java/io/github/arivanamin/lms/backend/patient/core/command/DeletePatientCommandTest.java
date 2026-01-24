package io.github.arivanamin.lms.backend.patient.core.command;

import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletePatientCommandTest implements BaseUnitTest {

    private final UUID id = UUID.randomUUID();

    @Mock
    private PatientStorage storage;

    @InjectMocks
    private DeletePatientCommand command;

    @Test
    void commandShouldCallStorageDelete () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted(id);
        thenVerifyCommandCallsDelete();
    }

    private void givenCommandWithMockStorage () {
    }

    private void whenCommandIsExecuted (UUID id) {
        command.execute(id);
    }

    private void thenVerifyCommandCallsDelete () {
        verify(storage, times(1)).delete(id);
    }

    @Test
    void commandShouldSendIdParameterToStorage () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted(id);
        thenVerifyIdIsSentToStorage();
    }

    private void thenVerifyIdIsSentToStorage () {
        ArgumentCaptor<UUID> idArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(storage).delete(idArgumentCaptor.capture());

        UUID capturedId = idArgumentCaptor.getValue();
        assertThat(id).isSameAs(capturedId);
    }
}
