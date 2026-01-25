package io.github.arivanamin.lms.backend.student.application.response;

import io.github.arivanamin.lms.backend.base.core.pagination.PageData;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.student.core.entity.Student;

import java.util.List;

public record ReadStudentsResponse(PageData pageData, List<StudentResponse> students) {

    public static ReadStudentsResponse of (PaginatedResponse<Student> paginatedResponse) {
        return new ReadStudentsResponse(paginatedResponse.pageData(), paginatedResponse.content()
            .stream()
            .map(StudentResponse::of)
            .toList());
    }
}
