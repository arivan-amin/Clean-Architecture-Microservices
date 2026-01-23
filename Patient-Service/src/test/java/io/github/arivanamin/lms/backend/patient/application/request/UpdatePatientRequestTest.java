package io.github.arivanamin.lms.backend.patient.application.request;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UpdatePatientRequestTest implements BaseUnitTest {

    @Test
    void toEntityShouldMapRequestToDomainEntity () {
        // given
        UpdatePatientRequest request = createRequest();
        UUID id = UUID.randomUUID();

        // when
        Patient entity = request.toEntity(id);

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

    private UpdatePatientRequest createRequest () {
        return new UpdatePatientRequest("Chris", "Martin", "chris.martin@example.com", 1995,
            Gender.MALE, "Woodward avenue 31th");
    }
}
