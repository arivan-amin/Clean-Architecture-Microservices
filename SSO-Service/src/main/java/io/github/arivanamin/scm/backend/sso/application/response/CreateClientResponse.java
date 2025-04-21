package io.github.arivanamin.scm.backend.sso.application.response;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateClientResponse {

    UUID id;

    public static CreateClientResponse of (UUID id) {
        return new CreateClientResponse(id);
    }
}
