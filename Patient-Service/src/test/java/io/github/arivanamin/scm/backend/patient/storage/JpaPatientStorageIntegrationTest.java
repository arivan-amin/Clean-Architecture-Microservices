package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.base.domain.gender.Gender;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseDatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaPatientStorageIntegrationTest extends BaseDatabaseTest {

    @Autowired
    private PatientRepository repository;
    private JpaPatientStorage storage;

    private int entitiesCount;
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

    private void whenFindAllIsCalled () {
        expectedPatients = storage.findAll(PaginationCriteria.of(0, entitiesCount))
            .getContent();
    }

    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<Patient> result) {
        assertThat(result.size()).isEqualTo(entitiesCount);
    }

    private void givenRepositoryWithSamplePatientsAndOnePatientExtracted () {
        givenRepositoryWithSavedPatients();
        expectedId = repository.findAll()
            .get(FAKER.number()
                .numberBetween(0, entitiesCount))
            .getId();
        expectedPatient = repository.findAll()
            .stream()
            .filter(patient -> patient.getId()
                .equals(expectedId))
            .findFirst()
            .orElseThrow()
            .toDomain();
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

    private void givenRepositoryWithSavedPatients () {
        entitiesCount = FAKER.number()
            .numberBetween(3, 10);
        for (int i = 0; i < entitiesCount; i++) {
            JpaPatient entity = createSamplePatient();
            repository.save(entity);
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
            .getContent()
            .size()).isEqualTo(entitiesCount - 1);
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
        expectedPatient.setFirstName(Instancio.create(String.class));
        expectedPatient.setLastName(Instancio.create(String.class));
        expectedPatient.setEmail(FAKER.internet()
            .emailAddress());
        expectedPatient.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        expectedPatient.setGender(Instancio.create(Gender.class));
        expectedPatient.setAddress(Instancio.create(String.class));
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

    @Test
    void updateShouldPreserveIntegrityOfAuditData () throws InterruptedException {
        // given
        JpaPatient savedPatient = repository.save(createSamplePatient());
        log.info("savedPatient in repository = {}", savedPatient);
        LocalDateTime createdAt = savedPatient.getCreatedAt();
        LocalDateTime updatedAt = savedPatient.getUpdatedAt();
        UUID id = savedPatient.getId();

        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
        updatedPatient.setFirstName(Instancio.create(String.class));
        updatedPatient.setLastName(Instancio.create(String.class));
        updatedPatient.setEmail(FAKER.internet()
            .emailAddress());
        updatedPatient.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        updatedPatient.setGender(Instancio.create(Gender.class));
        updatedPatient.setAddress(Instancio.create(String.class));
        log.info("updatedPatient = {}", updatedPatient);

        // when
        storage.update(updatedPatient);

        // then
        JpaPatient result = repository.findById(id)
            .orElseThrow();
        log.info("result of after update = {}", result);
        assertThat(result.getCreatedAt()).isEqualTo(createdAt);
        assertThat(result.getUpdatedAt()).isNotEqualTo(updatedAt)
            .isAfter(updatedAt);
    }
}
