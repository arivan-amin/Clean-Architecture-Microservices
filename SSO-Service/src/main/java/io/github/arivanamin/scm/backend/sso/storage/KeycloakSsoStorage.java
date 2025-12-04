package io.github.arivanamin.scm.backend.sso.storage;

import io.github.arivanamin.scm.backend.sso.core.entity.Client;
import io.github.arivanamin.scm.backend.sso.core.persistence.SsoStorage;
import io.github.arivanamin.scm.backend.sso.core.secret.ClientSecretGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class KeycloakSsoStorage implements SsoStorage {

    private final ClientSecretGenerator secretGenerator;

    @Override
    public Optional<Client> findById (UUID id) {
        return Optional.of(null);
    }

    @Override
    public UUID create (Client client) {
        secretGenerator.generateSecret();
        return null;
    }
}
