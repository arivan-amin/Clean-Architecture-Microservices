package com.arivanamin.healthcare.backend.sso.core.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException () {
        super("client by the requested id not found");
    }
}
