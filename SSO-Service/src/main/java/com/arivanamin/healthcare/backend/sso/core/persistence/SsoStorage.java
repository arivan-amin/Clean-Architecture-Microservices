package com.arivanamin.healthcare.backend.sso.core.persistence;

import com.arivanamin.healthcare.backend.sso.core.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface SsoStorage {

    Optional<Client> findById (UUID id);

    UUID create (Client client);
}
