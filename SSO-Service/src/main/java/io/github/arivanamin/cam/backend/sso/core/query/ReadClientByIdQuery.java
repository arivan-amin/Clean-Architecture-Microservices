package io.github.arivanamin.cam.backend.sso.core.query;

import io.github.arivanamin.cam.backend.sso.core.entity.Client;
import io.github.arivanamin.cam.backend.sso.core.exception.ClientNotFoundException;
import io.github.arivanamin.cam.backend.sso.core.persistence.SsoStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ReadClientByIdQuery {

    private final SsoStorage storage;

    public Client execute (UUID id) {
        return storage.findById(id)
            .orElseThrow(ClientNotFoundException::new);
    }
}
