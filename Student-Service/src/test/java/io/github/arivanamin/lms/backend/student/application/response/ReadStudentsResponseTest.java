package io.github.arivanamin.lms.backend.student.application.response;

import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static io.github.arivanamin.lms.backend.student.StudentTestData.studentsList;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ReadStudentsResponseTest implements BaseUnitTest {

    @Test
    void factoryMethodShouldMapDomainEntityToReadResponse () {
        // given
        PaginatedResponse<Student> response = PaginatedResponse.of(PAGE_DATA, studentsList());

        // when
        ReadStudentsResponse result = ReadStudentsResponse.of(response);

        // then
        assertThat(result.pageData()).isEqualTo(response.pageData());
        assertThat(result.students()
            .size()).isEqualTo(response.content()
            .size());
    }
}
