package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JpaPatientTest implements BaseUnitTest {

    @Test
    void fromDomainShouldMapPatientCorrectly () {
        // given
        Patient patient = Instancio.create(Patient.class);

        // when
        JpaPatient jpaPatient = JpaPatient.fromDomain(patient);

        // then
        assertThat(jpaPatient.getId()).isEqualTo(patient.getId());
        assertThat(jpaPatient.getFirstName()).isEqualTo(patient.getFirstName());
        assertThat(jpaPatient.getLastName()).isEqualTo(patient.getLastName());
        assertThat(jpaPatient.getEmail()).isEqualTo(patient.getEmail());
        assertThat(jpaPatient.getDateOfBirth()).isEqualTo(patient.getDateOfBirth());
        assertThat(jpaPatient.getGender()).isEqualTo(patient.getGender());
        assertThat(jpaPatient.getAddress()).isEqualTo(patient.getAddress());
        assertThat(jpaPatient.getCreatedAt()).isEqualTo(patient.getCreatedAt());
        assertThat(jpaPatient.getUpdatedAt()).isEqualTo(patient.getUpdatedAt());
    }

    @Test
    void toDomainShouldMapPatientCorrectly () {
        // given
        JpaPatient jpaPatient = Instancio.create(JpaPatient.class);
        // when
        Patient patient = jpaPatient.toDomain();

        // then
        assertThat(patient.getId()).isEqualTo(jpaPatient.getId());
        assertThat(patient.getFirstName()).isEqualTo(jpaPatient.getFirstName());
        assertThat(patient.getLastName()).isEqualTo(jpaPatient.getLastName());
        assertThat(patient.getEmail()).isEqualTo(jpaPatient.getEmail());
        assertThat(patient.getDateOfBirth()).isEqualTo(jpaPatient.getDateOfBirth());
        assertThat(patient.getGender()).isEqualTo(jpaPatient.getGender());
        assertThat(patient.getAddress()).isEqualTo(jpaPatient.getAddress());
        assertThat(patient.getCreatedAt()).isEqualTo(jpaPatient.getCreatedAt());
        assertThat(patient.getUpdatedAt()).isEqualTo(jpaPatient.getUpdatedAt());
    }
}
