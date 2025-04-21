package io.github.arivanamin.scm.backend.sso.core.persistence;

import io.github.arivanamin.scm.backend.sso.core.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface SsoStorage {

    Optional<Client> findById (UUID id);

    UUID create (Client client);
}
