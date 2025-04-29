package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseDatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaPatientStorageIntegrationTest extends BaseDatabaseTest {

    @Autowired
    private PatientRepository repository;
    private JpaPatientStorage storage;

    private int numberOfSavedEntities;
    private UUID expectedId;

    private List<Patient> expectedPatients;
    private Patient expectedPatient;

    @BeforeEach
    void setUp () {
        storage = new JpaPatientStorage(repository);
    }

    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }

    @Test
    void findAllShouldReturnAllPatients () {
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
        expectedPatients = storage.findAll(PAGINATION_CRITERIA)
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
    void findByIdShouldReturnSinglePatient () {
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
        expectedPatient = storage.findById(sampleId)
            .orElseThrow();
    }

    private void thenAssertThatCorrectPatientIsReturned (Patient resultPatient) {
        assertThat(resultPatient).isEqualTo(expectedPatient);
    }

    @Test
    void deleteShouldDeletePatientInRepository () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenDeleteIsCalled();
        thenAssertThatEntityIsDeletedFromRepository();
    }

    private void whenDeleteIsCalled () {
        storage.delete(expectedId);
    }

    private void thenAssertThatEntityIsDeletedFromRepository () {
        assertThat(storage.findAll(PAGINATION_CRITERIA)
            .getContent()
            .size()).isEqualTo(numberOfSavedEntities - 1);
        assertThat(repository.findById(expectedId)).isEmpty();
    }

    @Test
    void createShouldSavePatientInRepository () {
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
        storage.create(expectedPatient);
    }

    private void thenAssertThatPatientIsCreatedInRepository () {
        assertThat(repository.findAll()
            .size()).isOne();
    }

    @Test
    void updateShouldUpdatePatientInRepository () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenUpdateIsCalledWithModifiedPatient();
        thenAssertThatPatientIsUpdatedInRepository();
    }

    private void whenUpdateIsCalledWithModifiedPatient () {
        expectedPatient.setFirstName(FAKER.zelda()
            .character());
        storage.update(expectedPatient);
    }

    private void thenAssertThatPatientIsUpdatedInRepository () {
        JpaPatient result = repository.findById(expectedId)
            .orElseThrow();
        assertThat(result.getFirstName()).isEqualTo(expectedPatient.getFirstName());
    }
}
