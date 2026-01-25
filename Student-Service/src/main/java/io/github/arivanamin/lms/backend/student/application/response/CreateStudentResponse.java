package io.github.arivanamin.lms.backend.student.application.response;

import java.util.UUID;

public record CreateStudentResponse(UUID id) {

    public static CreateStudentResponse of (UUID id) {
        return new CreateStudentResponse(id);
    }
}
