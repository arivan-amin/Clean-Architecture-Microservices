package io.github.arivanamin.cam.backend.sso.core.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException () {
        super("Client with the requested email already exists");
    }
}
