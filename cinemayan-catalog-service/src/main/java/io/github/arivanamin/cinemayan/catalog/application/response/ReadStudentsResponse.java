package io.github.arivanamin.cinemayan.catalog.application.response;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import io.github.arivanamin.cinemayan.core.domain.pagination.PageData;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginatedResponse;

import java.util.List;

public record ReadStudentsResponse(PageData pageData, List<StudentResponse> students) {

    public static ReadStudentsResponse of (PaginatedResponse<Student> paginatedResponse) {
        return new ReadStudentsResponse(paginatedResponse.pageData(), paginatedResponse.content()
            .stream()
            .map(StudentResponse::of)
            .toList());
    }
}
