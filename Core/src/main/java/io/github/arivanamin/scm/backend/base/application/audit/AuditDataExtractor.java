package io.github.arivanamin.scm.backend.base.application.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;

@RequiredArgsConstructor
@Slf4j
public class AuditDataExtractor {

    private final String serviceName;

    public AuditEvent extractAuditData (ProceedingJoinPoint joinPoint, Object result,
                                        long duration) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String requestURL = extractRequestUrl(method);
        String requestAnnotation = extractRequestAnnotation(method);

        String parameters = getMethodParameters(joinPoint);
        String response = getMethodReturnType(result);

        AuditEvent event = AuditEvent.builder()
            .serviceName(serviceName)
            .location(requestURL)
            .action(requestAnnotation)
            .data(parameters)
            .timestamp(toTimestampInMilliseconds(LocalDateTime.now()))
            .duration(duration)
            .response(response)
            .build();

        log.info("Created AuditEvent to be published = {}", event);
        return event;
    }

    private String extractRequestUrl (Method method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            return extractUrl(method.getAnnotation(GetMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(PostMapping.class)) {
            return extractUrl(method.getAnnotation(PostMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(PutMapping.class)) {
            return extractUrl(method.getAnnotation(PutMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return extractUrl(method.getAnnotation(DeleteMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(PatchMapping.class)) {
            return extractUrl(method.getAnnotation(PatchMapping.class)
                .value());
        }
        return "";
    }

    private String extractRequestAnnotation (AnnotatedElement method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            return "Read";
        }
        else if (method.isAnnotationPresent(PostMapping.class)) {
            return "Create";
        }
        else if (method.isAnnotationPresent(PutMapping.class)) {
            return "Update";
        }
        else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return "Delete";
        }
        else if (method.isAnnotationPresent(PatchMapping.class)) {
            return "Patch";
        }
        return "";
    }

    private static String getMethodParameters (ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
            .map(String::valueOf)
            .collect(Collectors.joining(","));
    }

    private static String getMethodReturnType (Object result) {
        return result == null ? "Void" : result.toString();
    }

    private String extractUrl (String... paths) {
        if (paths != null && paths.length > 0) {
            return String.join(", ", paths);
        }
        return "Unknown URL";
    }
}
