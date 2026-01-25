package io.github.arivanamin.lms.backend.audit;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toTimestampInMilliseconds;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class AuditEventTestData {

    public static AuditEvent defaultEvent () {
        return initializeEvent();
    }

    private static AuditEvent initializeEvent () {
        AuditEvent event = new AuditEvent();
        event.setId(UUID.randomUUID());
        event.setServiceName("test-service");
        event.setLocation("/users/account");
        event.setAction("Action");
        event.setData("Test data");
        event.setDuration(1000);
        event.setResponse("success");
        event.setTimestamp(toTimestampInMilliseconds(LocalDateTime.now()));
        return event;
    }

    public static AuditEvent eventWithRecordedAt (LocalDateTime recordedAt) {
        AuditEvent event = initializeEvent();
        event.setTimestamp(toTimestampInMilliseconds(recordedAt));
        return event;
    }
}
