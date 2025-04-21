package io.github.arivanamin.scm.backend.patient.application.response;

import lombok.Value;

import java.util.UUID;

@Value
public class CreatePatientResponse {

    UUID id;

    public static CreatePatientResponse of (UUID id) {
        return new CreatePatientResponse(id);
    }
}
