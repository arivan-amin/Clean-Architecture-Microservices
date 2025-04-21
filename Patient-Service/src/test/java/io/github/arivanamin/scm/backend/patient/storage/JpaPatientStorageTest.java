package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
class JpaPatientStorageTest implements BaseUnitTest {

    @Mock
    PatientRepository repository;

    @InjectMocks
    JpaPatientStorage storage;

    @Test
    void shouldUpdateAllFieldsOfPatient () {
        // given
        ArgumentCaptor<JpaPatient> captor = ArgumentCaptor.forClass(JpaPatient.class);
        Patient patientToBeUpdated = Instancio.create(Patient.class);
        when(repository.findById(patientToBeUpdated.getId())).thenReturn(createRandomPatient());

        // when
        storage.update(patientToBeUpdated);
        verify(repository).save(captor.capture());
        JpaPatient result = captor.getValue();

        // then
        assertThat(result.getId()).isEqualTo(patientToBeUpdated.getId());
        assertThat(result.getFirstName()).isEqualTo(patientToBeUpdated.getFirstName());
        assertThat(result.getLastName()).isEqualTo(patientToBeUpdated.getLastName());
        assertThat(result.getEmail()).isEqualTo(patientToBeUpdated.getEmail());
        assertThat(result.getDateOfBirth()).isEqualTo(patientToBeUpdated.getDateOfBirth());
        assertThat(result.getGender()).isEqualTo(patientToBeUpdated.getGender());
        assertThat(result.getAddress()).isEqualTo(patientToBeUpdated.getAddress());
    }

    private static @NotNull Optional<JpaPatient> createRandomPatient () {
        return Optional.of(Instancio.create(JpaPatient.class));
    }

    @Test
    void shouldPreventAuditDataModification () {
        // given
        ArgumentCaptor<JpaPatient> captor = ArgumentCaptor.forClass(JpaPatient.class);

        Patient patientToBeUpdated = Instancio.create(Patient.class);
        patientToBeUpdated.setCreatedAt(null);
        patientToBeUpdated.setUpdatedAt(null);

        when(repository.findById(patientToBeUpdated.getId())).thenReturn(createRandomPatient());

        // when
        storage.update(patientToBeUpdated);
        verify(repository).save(captor.capture());
        JpaPatient result = captor.getValue();

        // then
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getUpdatedAt()).isNotNull();
    }
}
