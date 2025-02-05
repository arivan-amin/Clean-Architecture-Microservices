package com.arivanamin.healthcare.backend.patient.application.request;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CreatePatientRequestTest implements BaseUnitTest {

    @Test
    void shouldMapRequestToEntityCorrectly () {
        // given
        CreatePatientRequest request = RANDOM.nextObject(CreatePatientRequest.class);
        request.setDateOfBirth(toTimestampInMilliseconds(LocalDateTime.now()));

        // when
        Patient entity = request.toEntity();

        // then
        assertThat(request.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(request.getLastName()).isEqualTo(entity.getLastName());
        assertThat(request.getEmail()).isEqualTo(entity.getEmail());
        assertThat(toLocalDateTime(request.getDateOfBirth()).toLocalDate()).isEqualTo(
            entity.getDateOfBirth());
        assertThat(request.getGender()).isEqualTo(entity.getGender());
        assertThat(request.getAddress()).isEqualTo(entity.getAddress());
    }
}
