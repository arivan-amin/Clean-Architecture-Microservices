package io.github.arivanamin.lms.backend.student.storage.entity;

import io.github.arivanamin.lms.backend.student.StudentTestData;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StudentEntityTest implements BaseUnitTest {

    @Test
    void shouldMapDomainToEntity () {
        // given
        Student student = StudentTestData.withDefaultEmail();

        // when
        StudentEntity result = StudentEntity.fromDomain(student);

        // then
        assertThat(result.getId()).isEqualTo(student.getId());
        assertThat(result.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(result.getLastName()).isEqualTo(student.getLastName());
        assertThat(result.getEmail()).isEqualTo(student.getEmail());
        assertThat(result.getPhoneNumber()).isEqualTo(student.getPhoneNumber());
        assertThat(result.getDateOfBirth()).isEqualTo(student.getDateOfBirth());
        assertThat(result.getGender()).isEqualTo(student.getGender());
        assertThat(result.getStatus()).isEqualTo(student.getStatus());
        assertThat(result.getGradeLevel()).isEqualTo(student.getGradeLevel());
        assertThat(result.getAddress()).isEqualTo(student.getAddress());
    }

    @Test
    void shouldMapEntityToDomain () {
        // given
        StudentEntity entity = StudentEntity.fromDomain(StudentTestData.withDefaultEmail());

        // when
        Student result = entity.toDomain();

        // then
        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(result.getLastName()).isEqualTo(entity.getLastName());
        assertThat(result.getEmail()).isEqualTo(entity.getEmail());
        assertThat(result.getPhoneNumber()).isEqualTo(entity.getPhoneNumber());
        assertThat(result.getDateOfBirth()).isEqualTo(entity.getDateOfBirth());
        assertThat(result.getGender()).isEqualTo(entity.getGender());
        assertThat(result.getStatus()).isEqualTo(entity.getStatus());
        assertThat(result.getGradeLevel()).isEqualTo(entity.getGradeLevel());
        assertThat(result.getAddress()).isEqualTo(entity.getAddress());
    }
}
