package io.github.arivanamin.lms.backend.student.storage;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.storage.entity.StudentEntity;
import io.github.arivanamin.lms.backend.student.storage.repository.StudentRepository;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseDatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.github.arivanamin.lms.backend.student.StudentTestData.studentsList;
import static io.github.arivanamin.lms.backend.student.StudentTestData.withDefaultEmail;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaStudentStorageIT extends BaseDatabaseTest {

    private final int entitiesCount = 3;

    @Autowired
    private StudentRepository repository;
    private DatabaseStudentStorage storage;
    private UUID expectedId;

    private List<Student> expectedStudents;
    private Student expectedStudent;

    @BeforeEach
    void setUp () {
        storage = new DatabaseStudentStorage(repository);
    }

    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }

    @Test
    void findAllShouldReturnAllStudents () {
        givenRepositoryWithSavedStudents();
        whenFindAllIsCalled();
        thenAssertThatAllEntitiesOfRepositoryAreReturned(expectedStudents);
    }

    private void givenRepositoryWithSavedStudents () {
        List<Student> students = studentsList();
        students.stream()
            .map(StudentEntity::fromDomain)
            .toList();
        for (Student student : students) {
            repository.save(StudentEntity.fromDomain(student));
        }
    }

    private void whenFindAllIsCalled () {
        expectedStudents = storage.findAll(PaginationCriteria.of(0, entitiesCount))
            .content();
    }

    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<Student> result) {
        assertThat(result).hasSize(entitiesCount);
    }

    @Test
    void findByIdShouldReturnSingleStudent () {
        givenRepositoryWithSampleStudentsAndOneStudentExtracted();
        whenFindByIdIsCalled(expectedId);
        thenAssertThatCorrectStudentIsReturned(expectedStudent);
    }

    private void givenRepositoryWithSampleStudentsAndOneStudentExtracted () {
        givenRepositoryWithSavedStudents();
        expectedId = repository.findAll()
            .get(1)
            .getId();
        expectedStudent = repository.findAll()
            .stream()
            .filter(student -> student.getId()
                .equals(expectedId))
            .findFirst()
            .orElseThrow()
            .toDomain();
    }

    private void whenFindByIdIsCalled (UUID sampleId) {
        expectedStudent = storage.findById(sampleId)
            .orElseThrow();
    }

    private void thenAssertThatCorrectStudentIsReturned (Student resultStudent) {
        assertThat(resultStudent).isEqualTo(expectedStudent);
    }

    @Test
    void deleteShouldDeleteStudentInRepository () {
        givenRepositoryWithSampleStudentsAndOneStudentExtracted();
        whenDeleteIsCalled();
        thenAssertThatEntityIsDeletedFromRepository();
    }

    private void whenDeleteIsCalled () {
        storage.delete(expectedId);
    }

    private void thenAssertThatEntityIsDeletedFromRepository () {
        assertThat(storage.findAll(PaginationCriteria.of(0, entitiesCount))
            .content()).hasSize(entitiesCount - 1);
        assertThat(repository.findById(expectedId)).isEmpty();
    }

    @Test
    void createShouldSaveStudentInRepository () {
        givenValidSampleStudent();
        whenCreateIsCalled();
        thenAssertThatStudentIsCreatedInRepository();
    }

    private void givenValidSampleStudent () {
        expectedStudent = withDefaultEmail();
    }

    private void whenCreateIsCalled () {
        storage.create(expectedStudent);
    }

    private void thenAssertThatStudentIsCreatedInRepository () {
        assertThat(repository.findAll()
            .size()).isOne();
    }

    @Test
    void updateShouldUpdateStudentInRepository () {
        givenRepositoryWithSampleStudentsAndOneStudentExtracted();
        whenUpdateIsCalledWithModifiedStudent();
        thenAssertThatStudentIsUpdatedInRepository();
    }

    private void whenUpdateIsCalledWithModifiedStudent () {
        expectedStudent.setFirstName("mike");
        expectedStudent.setLastName("smith");
        expectedStudent.setEmail("john.smith@example.com");
        expectedStudent.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        expectedStudent.setGender(Gender.FEMALE);
        expectedStudent.setAddress("Maple avenue 55th, Los Angeles");
        storage.update(expectedStudent);
    }

    private void thenAssertThatStudentIsUpdatedInRepository () {
        StudentEntity result = repository.findById(expectedId)
            .orElseThrow();
        assertThat(result.getFirstName()).isEqualTo(expectedStudent.getFirstName());
        assertThat(result.getLastName()).isEqualTo(expectedStudent.getLastName());
        assertThat(result.getEmail()).isEqualTo(expectedStudent.getEmail());
        assertThat(result.getDateOfBirth()).isEqualTo(expectedStudent.getDateOfBirth());
        assertThat(result.getGender()).isEqualTo(expectedStudent.getGender());
        assertThat(result.getAddress()).isEqualTo(expectedStudent.getAddress());
    }
}
