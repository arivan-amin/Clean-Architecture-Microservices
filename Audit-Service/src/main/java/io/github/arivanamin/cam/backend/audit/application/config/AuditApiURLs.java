package io.github.arivanamin.cam.backend.audit.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class AuditApiURLs {

    public static final String PROTECTED_API = "/audits/protected";

    public static final String GET_EVENTS_URL = PROTECTED_API + "/v1/events";
    public static final String GET_EVENT_BY_CRITERIA_URL = PROTECTED_API + "/v1/events/criteria";
    public static final String GET_EVENT_BY_ID_URL = PROTECTED_API + "/v1/events/{id}";
}
