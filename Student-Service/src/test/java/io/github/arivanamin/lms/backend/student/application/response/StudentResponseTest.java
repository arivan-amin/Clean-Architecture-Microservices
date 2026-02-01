package io.github.arivanamin.lms.backend.student.application.response;

import io.github.arivanamin.lms.backend.student.StudentTestData;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StudentResponseTest implements BaseUnitTest {

    @Test
    void factoryMethodShouldMapEntityToResponse () {
        // given
        Student student = StudentTestData.withDefaultEmail();

        // when
        StudentResponse response = StudentResponse.of(student);

        // then
        assertThat(response.getId()).isEqualTo(student.getId());
        assertThat(response.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(response.getLastName()).isEqualTo(student.getLastName());
        assertThat(response.getEmail()).isEqualTo(student.getEmail());
        assertThat(response.getDateOfBirth()).isEqualTo(student.getDateOfBirth());
        assertThat(response.getGender()).isEqualTo(student.getGender());
        assertThat(response.getAddress()).isEqualTo(student.getAddress());
        assertThat(response.getCreatedAt()).isEqualTo(student.getCreatedAt());
        assertThat(response.getUpdatedAt()).isEqualTo(student.getUpdatedAt());
    }
}
