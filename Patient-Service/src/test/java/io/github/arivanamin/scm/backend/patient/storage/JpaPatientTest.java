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
    void fromDomainShouldMapDomainEntityToStorageEntity () {
        // given
        Patient domainEntity = Instancio.create(Patient.class);

        // when
        JpaPatient storageEntity = JpaPatient.fromDomain(domainEntity);

        // then
        assertThat(storageEntity.getId()).isEqualTo(domainEntity.getId());
        assertThat(storageEntity.getFirstName()).isEqualTo(domainEntity.getFirstName());
        assertThat(storageEntity.getLastName()).isEqualTo(domainEntity.getLastName());
        assertThat(storageEntity.getEmail()).isEqualTo(domainEntity.getEmail());
        assertThat(storageEntity.getDateOfBirth()).isEqualTo(domainEntity.getDateOfBirth());
        assertThat(storageEntity.getGender()).isEqualTo(domainEntity.getGender());
        assertThat(storageEntity.getAddress()).isEqualTo(domainEntity.getAddress());
        assertThat(storageEntity.getCreatedAt()).isEqualTo(domainEntity.getCreatedAt());
        assertThat(storageEntity.getUpdatedAt()).isEqualTo(domainEntity.getUpdatedAt());
    }

    @Test
    void toDomainShouldMapStorageEntityToDomainEntity () {
        // given
        JpaPatient storageEntity = Instancio.create(JpaPatient.class);

        // when
        Patient domainEntity = storageEntity.toDomain();

        // then
        assertThat(domainEntity.getId()).isEqualTo(storageEntity.getId());
        assertThat(domainEntity.getFirstName()).isEqualTo(storageEntity.getFirstName());
        assertThat(domainEntity.getLastName()).isEqualTo(storageEntity.getLastName());
        assertThat(domainEntity.getEmail()).isEqualTo(storageEntity.getEmail());
        assertThat(domainEntity.getDateOfBirth()).isEqualTo(storageEntity.getDateOfBirth());
        assertThat(domainEntity.getGender()).isEqualTo(storageEntity.getGender());
        assertThat(domainEntity.getAddress()).isEqualTo(storageEntity.getAddress());
        assertThat(domainEntity.getCreatedAt()).isEqualTo(storageEntity.getCreatedAt());
        assertThat(domainEntity.getUpdatedAt()).isEqualTo(storageEntity.getUpdatedAt());
    }
}
