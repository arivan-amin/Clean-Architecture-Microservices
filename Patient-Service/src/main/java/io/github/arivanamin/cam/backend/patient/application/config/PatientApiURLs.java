package io.github.arivanamin.cam.backend.patient.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class PatientApiURLs {

    public static final String PROTECTED_API = "/patients/protected";
    public static final String PUBLIC_API = "/patients/public";

    public static final String GET_PATIENTS_URL = PROTECTED_API + "/v1/accounts";
    public static final String GET_PATIENT_BY_ID_URL = PROTECTED_API + "/v1/accounts/{id}";
    public static final String CREATE_PATIENT_URL = PROTECTED_API + "/v1/accounts";
    public static final String UPDATE_PATIENT_URL = PROTECTED_API + "/v1/accounts/{id}";
    public static final String DELETE_PATIENT_URL = PROTECTED_API + "/v1/accounts/{id}";
}
