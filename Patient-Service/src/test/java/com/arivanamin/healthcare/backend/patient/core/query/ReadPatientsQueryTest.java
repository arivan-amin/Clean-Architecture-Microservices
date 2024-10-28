package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReadPatientsQueryTest implements BaseUnitTest {
    
    private final List<Patient> patients = List.of();
    private PatientPersistence persistence;
    private ReadPatientsQuery query;
    
    @Test
    void shouldCallPersistenceFindAll () {
        givenQueryWithMockPersistence();
        whenQueryIsExecuted();
        thenVerifyQueryCallsFindAll();
    }
    
    private void thenVerifyQueryCallsFindAll () {
        verify(persistence, times(1)).findAll();
    }
    
    private List<Patient> whenQueryIsExecuted () {
        return query.execute();
    }
    
    @Test
    void shouldReturnResultOfPersistenceFindAll () {
        givenQueryWithMockPersistence();
        List<Patient> result = whenQueryIsExecuted();
        thenVerifyFindAllResultIsReturned(result);
    }
    
    private void givenQueryWithMockPersistence () {
        persistence = mock(PatientPersistence.class);
        when(persistence.findAll()).thenReturn(patients);
        query = new ReadPatientsQuery(persistence);
    }
    
    private void thenVerifyFindAllResultIsReturned (List<Patient> result) {
        assertThat(patients).isSameAs(result);
    }
}
