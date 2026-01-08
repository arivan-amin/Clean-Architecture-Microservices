package io.github.arivanamin.scm.backend.patient.application.response;

import io.github.arivanamin.scm.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static io.github.arivanamin.scm.backend.patient.PatientTestData.patientsList;
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
        assertThat(result.getPageData()).isEqualTo(response.getPageData());
        assertThat(result.getPatients()
            .size()).isEqualTo(response.getContent()
            .size());
    }
}
