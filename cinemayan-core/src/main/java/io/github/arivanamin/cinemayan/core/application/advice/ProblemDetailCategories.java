package io.github.arivanamin.cinemayan.core.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class ProblemDetailCategories {

    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    public static final String RESOURCE_CONFLICT = "RESOURCE_CONFLICT";
    public static final String MISSING_PARAMETER = "MISSING_PARAMETER";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
}
