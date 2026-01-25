package io.github.arivanamin.lms.backend.student.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class StudentApiURLs {

    public static final String PROTECTED_API = "/students/protected";
    public static final String PUBLIC_API = "/students/public";
    public static final String ACCOUNT_BY_ID = "/v1/accounts/{id}";

    public static final String GET_STUDENTS_URL = PROTECTED_API + "/v1/accounts";
    public static final String GET_STUDENT_BY_ID_URL = PROTECTED_API + ACCOUNT_BY_ID;
    public static final String CREATE_STUDENT_URL = PROTECTED_API + "/v1/accounts";
    public static final String UPDATE_STUDENT_URL = PROTECTED_API + ACCOUNT_BY_ID;
    public static final String DELETE_STUDENT_URL = PROTECTED_API + ACCOUNT_BY_ID;
}
