package io.github.arivanamin.cam.backend.sso.application.response;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateClientResponse {

    UUID id;

    public static CreateClientResponse of (UUID id) {
        return new CreateClientResponse(id);
    }
}
