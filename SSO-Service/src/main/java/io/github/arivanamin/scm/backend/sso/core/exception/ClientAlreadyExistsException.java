package io.github.arivanamin.scm.backend.sso.core.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException () {
        super("Client with the requested email already exists");
    }
}
