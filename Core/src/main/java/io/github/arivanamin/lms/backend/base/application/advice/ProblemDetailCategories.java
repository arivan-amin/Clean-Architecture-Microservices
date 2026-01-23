package io.github.arivanamin.lms.backend.base.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public enum ProblemDetailCategories {
    ;

    public static final String RESOURCE_NOT_FOUND = "Resource Not Found";

    public static final String MISSING_PARAMETER = "Missing Parameter";

    public static final String INTERNAL_ERROR = "Internal Error";
}
