package com.arivanamin.healthcare.backend.sso.storage;

import com.arivanamin.healthcare.backend.sso.core.entity.Client;
import com.arivanamin.healthcare.backend.sso.core.persistence.SsoStorage;
import com.arivanamin.healthcare.backend.sso.core.secret.ClientSecretGenerator;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public UUID create (Client client) {
        secretGenerator.generateSecret();
        return null;
    }
}
