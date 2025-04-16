package io.github.arivanamin.cam.backend.patient.storage;

import io.github.arivanamin.cam.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class JpaPatientStorageTest implements BaseUnitTest {

    @Mock
    PatientRepository repository;

    @InjectMocks
    JpaPatientStorage persistence;

    @Test
    void shouldReturnAllPatientsFromRepository () {
        // given

        // when

        // then
    }
}
