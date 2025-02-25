package com.arivanamin.healthcare.backend.sso.core.query;

import com.arivanamin.healthcare.backend.sso.core.entity.Client;
import com.arivanamin.healthcare.backend.sso.core.exception.ClientNotFoundException;
import com.arivanamin.healthcare.backend.sso.core.persistence.SsoStorage;
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
