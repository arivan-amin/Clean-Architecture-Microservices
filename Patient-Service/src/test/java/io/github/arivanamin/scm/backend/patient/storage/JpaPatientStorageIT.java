package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.base.core.gender.Gender;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseDatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.github.arivanamin.scm.backend.patient.PatientTestData.patientsList;
import static io.github.arivanamin.scm.backend.patient.PatientTestData.withDefaultEmail;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaPatientStorageIT extends BaseDatabaseTest {

    @Autowired
    private PatientRepository repository;
    private JpaPatientStorage storage;

    private final int entitiesCount = 3;
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

    private void givenRepositoryWithSamplePatientsAndOnePatientExtracted () {
        givenRepositoryWithSavedPatients();
        expectedId = repository.findAll()
            .get(1)
            .getId();
        expectedPatient = repository.findAll()
            .stream()
            .filter(patient -> patient.getId()
                .equals(expectedId))
            .findFirst()
            .orElseThrow()
            .toDomain();
    }

    private void whenFindAllIsCalled () {
        expectedPatients = storage.findAll(PaginationCriteria.of(0, entitiesCount))
            .getContent();
    }

    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<Patient> result) {
        assertThat(result).hasSize(entitiesCount);
    }

    @Test
    void findByIdShouldReturnSinglePatient () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenFindByIdIsCalled(expectedId);
        thenAssertThatCorrectPatientIsReturned(expectedPatient);
    }

    private void givenRepositoryWithSavedPatients () {
        List<Patient> patients = patientsList();
        patients.stream()
            .map(JpaPatient::fromDomain)
            .toList();
        for (Patient patient : patients) {
            repository.save(JpaPatient.fromDomain(patient));
        }
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
        assertThat(storage.findAll(PaginationCriteria.of(0, entitiesCount))
            .getContent()).hasSize(entitiesCount - 1);
        assertThat(repository.findById(expectedId)).isEmpty();
    }

    @Test
    void createShouldSavePatientInRepository () {
        givenValidSamplePatient();
        whenCreateIsCalled();
        thenAssertThatPatientIsCreatedInRepository();
    }

    private void givenValidSamplePatient () {
        expectedPatient = withDefaultEmail();
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
        expectedPatient.setFirstName("mike");
        expectedPatient.setLastName("smith");
        expectedPatient.setEmail("john.smith@example.com");
        expectedPatient.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        expectedPatient.setGender(Gender.FEMALE);
        expectedPatient.setAddress("Maple avenue 55th, Los Angeles");
        storage.update(expectedPatient);
    }

    private void thenAssertThatPatientIsUpdatedInRepository () {
        JpaPatient result = repository.findById(expectedId)
            .orElseThrow();
        assertThat(result.getFirstName()).isEqualTo(expectedPatient.getFirstName());
        assertThat(result.getLastName()).isEqualTo(expectedPatient.getLastName());
        assertThat(result.getEmail()).isEqualTo(expectedPatient.getEmail());
        assertThat(result.getDateOfBirth()).isEqualTo(expectedPatient.getDateOfBirth());
        assertThat(result.getGender()).isEqualTo(expectedPatient.getGender());
        assertThat(result.getAddress()).isEqualTo(expectedPatient.getAddress());
    }
}
