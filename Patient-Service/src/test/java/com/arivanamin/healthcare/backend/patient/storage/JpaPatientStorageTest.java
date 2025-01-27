package com.arivanamin.healthcare.backend.patient.storage;

import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
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
