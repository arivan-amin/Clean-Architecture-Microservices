package io.github.arivanamin.lms.backend.audit;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.*;
import java.util.UUID;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class AuditEventTestData {

    public static AuditEvent defaultEvent () {
        return initializeEvent();
    }

    public static AuditEvent eventWithRecordedAt (LocalDateTime recordedAt) {
        AuditEvent event = initializeEvent();
        event.setRecordedAt(Instant.now());
        return event;
    }

    private static AuditEvent initializeEvent () {
        AuditEvent event = new AuditEvent();
        event.setId(UUID.randomUUID());
        event.setServiceName("test-service");
        event.setLocation("/users/account");
        event.setAction("Action");
        event.setData("Test data");
        event.setDuration(Duration.ofSeconds(1));
        event.setResponse("success");
        event.setRecordedAt(Instant.now());
        return event;
    }
}
