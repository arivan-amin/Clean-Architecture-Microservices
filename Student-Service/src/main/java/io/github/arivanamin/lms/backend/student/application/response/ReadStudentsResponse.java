package io.github.arivanamin.lms.backend.student.application.response;

import io.github.arivanamin.lms.backend.core.domain.pagination.PageData;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;

import java.util.List;

public record ReadStudentsResponse(PageData pageData, List<StudentResponse> students) {

    public static ReadStudentsResponse of (PaginatedResponse<Student> paginatedResponse) {
        return new ReadStudentsResponse(paginatedResponse.pageData(), paginatedResponse.content()
            .stream()
            .map(StudentResponse::of)
            .toList());
    }
}
