package io.github.arivanamin.scm.backend.patient.application.response;

import io.github.arivanamin.scm.backend.base.core.pagination.PageData;
import io.github.arivanamin.scm.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import lombok.Value;

import java.util.List;

@Value
public class ReadPatientsResponse {

    PageData pageData;
    List<PatientResponse> patients;

    public static ReadPatientsResponse of (PaginatedResponse<Patient> paginatedResponse) {
        return new ReadPatientsResponse(paginatedResponse.getPageData(),
            paginatedResponse.getContent()
                .stream()
                .map(PatientResponse::of)
                .toList());
    }
}
