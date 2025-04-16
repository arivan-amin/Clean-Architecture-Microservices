package io.github.arivanamin.cam.backend.patient.storage;

import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import io.github.arivanamin.cam.backend.testing.architecture.bases.BaseDatabaseTest;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaPatientStorageIntegrationTest implements BaseDatabaseTest {

    @Autowired
    private PatientRepository repository;
    private JpaPatientStorage persistence;

    private int numberOfSavedEntities;
    private UUID expectedId;

    private List<Patient> expectedPatients;
    private Patient expectedPatient;

    @BeforeEach
    void setUp () {
        persistence = new JpaPatientStorage(repository);
    }

    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }

    @Test
    void shouldReturnAllPatientsWhenFindAllIsCalled () {
        givenRepositoryWithSavedPatients();
        whenFindAllIsCalled();
        thenAssertThatAllEntitiesOfRepositoryAreReturned(expectedPatients);
    }

    private void givenRepositoryWithSavedPatients () {
        numberOfSavedEntities = FAKER.number()
            .numberBetween(3, 10);
        for (int i = 0; i < numberOfSavedEntities; i++) {
            JpaPatient entity = createSamplePatient();
            repository.save(entity);
        }
    }

    private void whenFindAllIsCalled () {
        expectedPatients = persistence.findAll(PAGINATION_CRITERIA)
            .getContent();
    }

    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<Patient> result) {
        assertThat(result.size()).isEqualTo(numberOfSavedEntities);
    }

    private static JpaPatient createSamplePatient () {
        JpaPatient entity = Instancio.create(JpaPatient.class);
        entity.setId(null);
        entity.setEmail(FAKER.internet()
            .emailAddress());
        entity.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        return entity;
    }

    @Test
    void shouldReturnSinglePatientWhenFindByIdIsCalled () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenFindByIdIsCalled(expectedId);
        thenAssertThatCorrectPatientIsReturned(expectedPatient);
    }

    private void givenRepositoryWithSamplePatientsAndOnePatientExtracted () {
        givenRepositoryWithSavedPatients();
        expectedId = repository.findAll()
            .get(FAKER.number()
                .numberBetween(0, numberOfSavedEntities))
            .getId();
        expectedPatient = repository.findAll()
            .stream()
            .filter(patient -> patient.getId()
                .equals(expectedId))
            .findFirst()
            .orElseThrow()
            .toDomain();
    }

    private void whenFindByIdIsCalled (UUID sampleId) {
        expectedPatient = persistence.findById(sampleId)
            .orElseThrow();
    }

    private void thenAssertThatCorrectPatientIsReturned (Patient resultPatient) {
        assertThat(resultPatient).isEqualTo(expectedPatient);
    }

    @Test
    void shouldDeletePatientInRepositoryWhenDeleteIsCalled () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenDeleteIsCalled();
        thenAssertThatEntityIsDeletedFromRepository();
    }

    private void whenDeleteIsCalled () {
        persistence.delete(expectedId);
    }

    private void thenAssertThatEntityIsDeletedFromRepository () {
        assertThat(persistence.findAll(PAGINATION_CRITERIA)
            .getContent()
            .size()).isEqualTo(numberOfSavedEntities - 1);
        assertThat(repository.findById(expectedId)).isEmpty();
    }

    @Test
    void shouldSavePatientInRepositoryWhenCreateIsCalled () {
        givenValidSamplePatient();
        whenCreateIsCalled();
        thenAssertThatPatientIsCreatedInRepository();
    }

    private void givenValidSamplePatient () {
        expectedPatient = createSamplePatient().toDomain();
        expectedPatient.setEmail(FAKER.internet()
            .emailAddress());
    }

    private void whenCreateIsCalled () {
        persistence.create(expectedPatient);
    }

    private void thenAssertThatPatientIsCreatedInRepository () {
        assertThat(repository.findAll()
            .size()).isOne();
    }

    @Test
    void shouldUpdatePatientInRepositoryWhenUpdateIsCalled () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenUpdateIsCalledWithModifiedPatient();
        thenAssertThatPatientIsUpdatedInRepository();
    }

    private void whenUpdateIsCalledWithModifiedPatient () {
        expectedPatient.setFirstName(FAKER.zelda()
            .character());
        persistence.update(expectedPatient);
    }

    private void thenAssertThatPatientIsUpdatedInRepository () {
        JpaPatient result = repository.findById(expectedId)
            .orElseThrow();
        assertThat(result.getFirstName()).isEqualTo(expectedPatient.getFirstName());
    }
}
