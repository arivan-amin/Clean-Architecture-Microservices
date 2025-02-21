package com.arivanamin.healthcare.backend.sso.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class SsoApiURLs {

    public static final String PROTECTED_API = "/sso/protected";
    public static final String PUBLIC_API = "/sso/public";

    // todo 2/21/25 - to be evaluated later !
    public static final String GET_CLIENT_BY_ID_URL = PUBLIC_API + "/v1/clients/{id}";
    public static final String CREATE_CLIENT_URL = PUBLIC_API + "/v1/clients";
}
