package io.github.arivanamin.lms.backend.patient.application.response;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static io.github.arivanamin.lms.backend.patient.PatientTestData.patientsList;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ReadPatientsResponseTest implements BaseUnitTest {

    @Test
    void factoryMethodShouldMapDomainEntityToReadResponse () {
        // given
        PaginatedResponse<Patient> response = PaginatedResponse.of(PAGE_DATA, patientsList());

        // when
        ReadPatientsResponse result = ReadPatientsResponse.of(response);

        // then
        assertThat(result.pageData()).isEqualTo(response.pageData());
        assertThat(result.patients()
            .size()).isEqualTo(response.content()
            .size());
    }
}
