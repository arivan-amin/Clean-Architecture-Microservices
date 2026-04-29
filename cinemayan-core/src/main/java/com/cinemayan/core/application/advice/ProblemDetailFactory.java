package com.cinemayan.core.application.advice;

import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;

import static com.cinemayan.core.application.advice.ProblemDetailProperties.*;

@Component
@RequiredArgsConstructor
public class ProblemDetailFactory {

    private final Clock clock;

    public ProblemDetail build (HttpStatus status, String title, String detail, String category,
                                String docUrl) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(title);
        problem.setDetail(detail);
        problem.setType(URI.create(docUrl));
        problem.setProperty(CATEGORY, category);
        problem.setProperty(TIMESTAMP, Instant.now(clock));
        problem.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        problem.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return problem;
    }
}
