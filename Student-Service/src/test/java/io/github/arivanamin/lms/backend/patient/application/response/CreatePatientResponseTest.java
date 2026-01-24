package io.github.arivanamin.lms.backend.patient.application.response;

import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CreatePatientResponseTest implements BaseUnitTest {

    @Test
    void factoryMethodShouldMapId () {
        // given
        UUID id = UUID.randomUUID();

        // when
        CreatePatientResponse response = CreatePatientResponse.of(id);

        // then
        assertThat(response.id()).isEqualTo(id);
    }
}
