package io.github.arivanamin.cam.backend.patient.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class PatientCaches {

    public static final String GET_ALL_PATIENTS_CACHE = "patientsCache";
    public static final String GET_PATIENT_BY_ID_CACHE = "patientByIdCache";
}
