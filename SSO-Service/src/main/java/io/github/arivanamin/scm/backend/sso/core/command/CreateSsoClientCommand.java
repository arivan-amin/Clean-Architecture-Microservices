package io.github.arivanamin.scm.backend.sso.core.command;

import io.github.arivanamin.scm.backend.sso.core.entity.Client;
import io.github.arivanamin.scm.backend.sso.core.exception.ClientAlreadyExistsException;
import io.github.arivanamin.scm.backend.sso.core.persistence.SsoStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateSsoClientCommand {

    private final SsoStorage storage;

    public UUID execute (Client client) {
        // todo 2/21/25 - is this the correct service to check for user duplication ?
        if (doesClientExist(client)) {
            throw new ClientAlreadyExistsException();
        }
        return storage.create(client);
    }

    private boolean doesClientExist (Client client) {
        return storage.findById(client.getId())
            .isPresent();
    }
}
