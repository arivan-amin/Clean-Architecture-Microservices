package io.github.arivanamin.lms.backend.patient.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public enum PatientCaches {
    ;

    public static final String GET_ALL_PATIENTS_CACHE = "patientsCache";
    public static final String GET_PATIENT_BY_ID_CACHE = "patientByIdCache";
}
