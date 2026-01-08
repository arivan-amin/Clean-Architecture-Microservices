package io.github.arivanamin.scm.backend.patient.application.request;

import io.github.arivanamin.scm.backend.base.core.gender.Gender;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.github.arivanamin.scm.backend.base.core.dates.TimestampHelper.toLocalDateTime;
import static io.github.arivanamin.scm.backend.base.core.dates.TimestampHelper.toTimestampInMilliseconds;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CreatePatientRequestTest implements BaseUnitTest {

    @Test
    void toDomainEntityShouldMapRequestToDomainEntity () {
        // given
        CreatePatientRequest request = getCreatePatientRequest();
        request.setDateOfBirth(toTimestampInMilliseconds(LocalDateTime.now()));

        // when
        Patient entity = request.toDomainEntity();

        // then
        assertThat(request.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(request.getLastName()).isEqualTo(entity.getLastName());
        assertThat(request.getEmail()).isEqualTo(entity.getEmail());
        assertThat(toLocalDateTime(request.getDateOfBirth()).toLocalDate()).isEqualTo(
            entity.getDateOfBirth());
        assertThat(request.getGender()).isEqualTo(entity.getGender());
        assertThat(request.getAddress()).isEqualTo(entity.getAddress());
    }

    private CreatePatientRequest getCreatePatientRequest () {
        CreatePatientRequest request = new CreatePatientRequest();
        request.setFirstName("some name");
        request.setLastName("some last name");
        request.setEmail("some email");
        request.setDateOfBirth(1999);
        request.setGender(Gender.MALE);
        request.setAddress("some address");
        return request;
    }
}
