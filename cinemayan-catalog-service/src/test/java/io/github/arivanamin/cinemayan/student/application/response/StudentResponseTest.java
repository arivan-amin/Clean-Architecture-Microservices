package io.github.arivanamin.cinemayan.student.application.response;

import io.github.arivanamin.cinemayan.student.StudentTestData;
import io.github.arivanamin.cinemayan.student.domain.entity.Student;
import io.github.arivanamin.cinemayan.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StudentResponseTest implements BaseUnitTest {

    @Test
    void shouldMapEntityToResponse () {
        // given
        Student student = StudentTestData.withDefaultEmail();

        // when
        StudentResponse response = StudentResponse.of(student);

        // then
        assertThat(response).usingRecursiveComparison()
            .isEqualTo(student);
    }
}
