package io.github.arivanamin.cinemayan.student.application.response;

import java.util.UUID;

public record CreateStudentResponse(UUID id) {

    public static CreateStudentResponse of (UUID id) {
        return new CreateStudentResponse(id);
    }
}
