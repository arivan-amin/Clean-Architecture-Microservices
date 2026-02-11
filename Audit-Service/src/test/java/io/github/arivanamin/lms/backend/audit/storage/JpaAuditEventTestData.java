package io.github.arivanamin.lms.backend.audit.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class JpaAuditEventTestData {

    public static JpaAuditEvent defaultEvent () {
        JpaAuditEvent event = new JpaAuditEvent();
        event.setId(UUID.randomUUID());
        event.setServiceName("user-service");
        event.setLocation("/accounts/activate");
        event.setAction("Pending");
        event.setData("Account activation");
        event.setDuration(Duration.ofSeconds(2));
        event.setResponse("activated");
        event.setRecordedAt(Instant.now());
        return event;
    }
}
