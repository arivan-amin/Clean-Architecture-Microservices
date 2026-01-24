package io.github.arivanamin.lms.backend.patient.application.response;

import io.github.arivanamin.lms.backend.base.core.pagination.PageData;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;

import java.util.List;

public record ReadPatientsResponse(PageData pageData, List<PatientResponse> patients) {

    public static ReadPatientsResponse of (PaginatedResponse<Patient> paginatedResponse) {
        return new ReadPatientsResponse(paginatedResponse.pageData(), paginatedResponse.content()
            .stream()
            .map(PatientResponse::of)
            .toList());
    }
}
