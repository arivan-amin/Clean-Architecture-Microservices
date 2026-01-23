package io.github.arivanamin.lms.backend.audit.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public enum JpaAuditEventTestData {
    ;

    public static JpaAuditEvent defaultEvent () {
        JpaAuditEvent event = new JpaAuditEvent();
        event.setId(UUID.randomUUID());
        event.setServiceName("user-service");
        event.setLocation("/accounts/activate");
        event.setAction("Pending");
        event.setData("Account activation");
        event.setDuration(2000);
        event.setResponse("activated");
        event.setRecordedAt(LocalDateTime.now());
        return event;
    }
}
