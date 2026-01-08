package io.github.arivanamin.scm.backend.patient.application.response;

import io.github.arivanamin.scm.backend.patient.PatientTestData;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PatientResponseTest implements BaseUnitTest {

    @Test
    void factoryMethodShouldMapEntityToResponse () {
        // given
        Patient patient = PatientTestData.withDefaultEmail();

        // when
        PatientResponse response = PatientResponse.of(patient);

        // then
        assertThat(response.getId()).isEqualTo(patient.getId());
        assertThat(response.getFirstName()).isEqualTo(patient.getFirstName());
        assertThat(response.getLastName()).isEqualTo(patient.getLastName());
        assertThat(response.getEmail()).isEqualTo(patient.getEmail());
        assertThat(response.getDateOfBirth()).isEqualTo(patient.getDateOfBirth());
        assertThat(response.getGender()).isEqualTo(patient.getGender());
        assertThat(response.getAddress()).isEqualTo(patient.getAddress());
        assertThat(response.getCreatedAt()).isEqualTo(patient.getCreatedAt());
        assertThat(response.getUpdatedAt()).isEqualTo(patient.getUpdatedAt());
    }
}
