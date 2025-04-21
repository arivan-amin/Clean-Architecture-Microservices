package io.github.arivanamin.scm.backend.patient.application.request;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CreatePatientRequestTest implements BaseUnitTest {

    @Test
    void shouldMapRequestToEntityCorrectly () {
        // given
        CreatePatientRequest request = Instancio.create(CreatePatientRequest.class);
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
