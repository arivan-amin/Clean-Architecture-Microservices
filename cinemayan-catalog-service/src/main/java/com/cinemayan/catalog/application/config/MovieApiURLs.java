package com.cinemayan.catalog.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class MovieApiURLs {

    public static final String PROTECTED_API = "/movies/protected";
    public static final String PUBLIC_API = "/movies/public";
    public static final String ACCOUNT_BY_ID = "/v1/accounts/{id}";

    public static final String GET_MOVIES_URL = PROTECTED_API + "/v1/accounts";
    public static final String GET_MOVIE_BY_ID_URL = PROTECTED_API + ACCOUNT_BY_ID;
    public static final String CREATE_MOVIE_URL = PROTECTED_API + "/v1/accounts";
    public static final String UPDATE_MOVIE_URL = PROTECTED_API + ACCOUNT_BY_ID;
    public static final String DELETE_MOVIE_URL = PROTECTED_API + ACCOUNT_BY_ID;
}
