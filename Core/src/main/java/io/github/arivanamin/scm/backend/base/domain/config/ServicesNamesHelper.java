package io.github.arivanamin.scm.backend.base.domain.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class ServicesNamesHelper {

    public static final String PATIENT_SERVICE = "patient";
    public static final String AUDIT_SERVICE = "audit";
    public static final String NOTIFICATION_SERVICE = "notification";
}
