package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.*;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

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
        assertThat(entity.getFirstName()).isEqualTo(request.getFirstName());
        assertThat(entity.getLastName()).isEqualTo(request.getLastName());
        assertThat(entity.getEmail()).isEqualTo(request.getEmail());
        assertThat(entity.getDateOfBirth()).isEqualTo(request.getDateOfBirth());
        assertThat(entity.getGender()).isEqualTo(request.getGender());
        assertThat(entity.getAddress()).isEqualTo(request.getAddress());
    }

    private UpdateStudentRequest createRequest () {
        return new UpdateStudentRequest("Chris", "Martin", "chris.martin@example.com",
            "07701234561", LocalDate.of(1995, 3, 7), Gender.MALE, StudentStatus.ENROLLED,
            GradeLevel.GRADE_5, "Woodward avenue 31th");
    }
}
