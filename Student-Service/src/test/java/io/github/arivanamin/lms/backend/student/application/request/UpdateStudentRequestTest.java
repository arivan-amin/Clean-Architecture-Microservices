package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.github.arivanamin.lms.backend.core.domain.dates.TimestampHelper.toLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UpdateStudentRequestTest implements BaseUnitTest {

    @Test
    void toEntityShouldMapRequestToDomainEntity () {
        // given
        UpdateStudentRequest request = createRequest();
        UUID id = UUID.randomUUID();

        // when
        Student entity = request.toEntity(id);

        // then
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getFirstName()).isEqualTo(request.firstName());
        assertThat(entity.getLastName()).isEqualTo(request.lastName());
        assertThat(entity.getEmail()).isEqualTo(request.email());
        assertThat(entity.getDateOfBirth()).isEqualTo(
            toLocalDateTime(request.dateOfBirth()).toLocalDate());
        assertThat(entity.getGender()).isEqualTo(request.gender());
        assertThat(entity.getAddress()).isEqualTo(request.address());
    }

    private UpdateStudentRequest createRequest () {
        return new UpdateStudentRequest("Chris", "Martin", "chris.martin@example.com", 1995,
            Gender.MALE, "Woodward avenue 31th");
    }
}
