package io.github.arivanamin.lms.backend.patient.application.response;

import java.util.UUID;

public record CreatePatientResponse(UUID id) {

    public static CreatePatientResponse of (UUID id) {
        return new CreatePatientResponse(id);
    }
}
