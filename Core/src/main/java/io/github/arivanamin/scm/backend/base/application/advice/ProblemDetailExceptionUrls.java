package io.github.arivanamin.scm.backend.base.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class ProblemDetailExceptionUrls {

    public static final String SPRING_DOCS_URI =
        "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework";

    public static final String RUNTIME_EXCEPTION_URL =
        "https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/RuntimeException" +
            ".html";

    public static final String EXCEPTION_URL =
        "https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Exception" +
            ".html";

    public static final String SPRING_RESOURCE_NOT_FOUND_EXCEPTION_URL =
        SPRING_DOCS_URI + "/web/servlet/resource/NoResourceFoundException.html";

    public static final String SPRING_REACTIVE_RESOURCE_NOT_FOUND_EXCEPTION_URL =
        SPRING_DOCS_URI + "/web/reactive/resource/NoResourceFoundException.html";

    public static final String HTTP_MESSAGE_NOT_READABLE_EXCEPTION_URL =
        SPRING_DOCS_URI + "/http/converter/HttpMessageNotReadableException.html";

    public static final String MISSING_PATH_VARIABLE_EXCEPTION_URL =
        SPRING_DOCS_URI + "/web/bind/MissingPathVariableException.html";

    public static final String METHOD_ARGUMENT_NOT_VALID_EXCEPTION_URL = SPRING_DOCS_URI +
        "/messaging/handler/annotation/support/MethodArgumentNotValidException.html";

    public static final String MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION_URL =
        SPRING_DOCS_URI + "/web/bind/MissingServletRequestParameterException.html";
}
