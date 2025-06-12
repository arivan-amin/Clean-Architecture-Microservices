package io.github.arivanamin.scm.backend.patient.application.response;

import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ReadPatientsResponseTest implements BaseUnitTest {

    @Test
    void factoryMethodShouldMapDomainEntityToReadResponse () {
        // given
        PaginatedResponse<Patient> response =
            PaginatedResponse.of(PAGE_DATA, createSamplePatientsList());

        // when
        ReadPatientsResponse result = ReadPatientsResponse.of(response);

        // then
        assertThat(result.getPageData()).isEqualTo(response.getPageData());
        assertThat(result.getPatients()
            .size()).isEqualTo(response.getContent()
            .size());
    }

    private static @NotNull List<Patient> createSamplePatientsList () {
        return List.of(Instancio.create(Patient.class), Instancio.create(Patient.class),
            Instancio.create(Patient.class));
    }
}
