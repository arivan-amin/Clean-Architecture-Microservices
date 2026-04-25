package io.github.arivanamin.cinemayan.core.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class ProblemDetailProperties {

    public static final String CATEGORY = "category";
    public static final String TIMESTAMP = "timestamp";

    public static final String TRACE_ID_KEY = "TRACE_ID";
    public static final String SPAN_ID_KEY = "SPAN_ID";

    public static final String TRACE_ID_VALUE = MDC.get("traceId");
    public static final String SPAN_ID_VALUE = MDC.get("spanId");
    
}
