package io.github.arivanamin.scm.backend.patient.application.request;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UpdatePatientRequestTest implements BaseUnitTest {

    @Test
    void toEntityShouldMapRequestToDomainEntity () {
        // given
        UpdatePatientRequest request = Instancio.create(UpdatePatientRequest.class);
        UUID id = UUID.randomUUID();

        // when
        Patient entity = request.toEntity(id);

        // then
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getFirstName()).isEqualTo(request.getFirstName());
        assertThat(entity.getLastName()).isEqualTo(request.getLastName());
        assertThat(entity.getEmail()).isEqualTo(request.getEmail());
        assertThat(entity.getDateOfBirth()).isEqualTo(
            toLocalDateTime(request.getDateOfBirth()).toLocalDate());
        assertThat(entity.getGender()).isEqualTo(request.getGender());
        assertThat(entity.getAddress()).isEqualTo(request.getAddress());
    }
}
