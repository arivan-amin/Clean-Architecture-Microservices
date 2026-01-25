package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;
import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toTimestampInMilliseconds;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CreateStudentRequestTest implements BaseUnitTest {

    @Test
    void toDomainEntityShouldMapRequestToDomainEntity () {
        // given
        CreateStudentRequest request = getCreateStudentRequest();
        request.setDateOfBirth(toTimestampInMilliseconds(LocalDateTime.now()));

        // when
        Student entity = request.toDomainEntity();

        // then
        assertThat(request.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(request.getLastName()).isEqualTo(entity.getLastName());
        assertThat(request.getEmail()).isEqualTo(entity.getEmail());
        assertThat(toLocalDateTime(request.getDateOfBirth()).toLocalDate()).isEqualTo(
            entity.getDateOfBirth());
        assertThat(request.getGender()).isEqualTo(entity.getGender());
        assertThat(request.getAddress()).isEqualTo(entity.getAddress());
    }

    private CreateStudentRequest getCreateStudentRequest () {
        CreateStudentRequest request = new CreateStudentRequest();
        request.setFirstName("some name");
        request.setLastName("some last name");
        request.setEmail("some email");
        request.setDateOfBirth(1999);
        request.setGender(Gender.MALE);
        request.setAddress("some address");
        return request;
    }
}
