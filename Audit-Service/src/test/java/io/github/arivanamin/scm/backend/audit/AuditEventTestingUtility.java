package io.github.arivanamin.scm.backend.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

import java.time.LocalDateTime;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class AuditEventTestingUtility {

    public static AuditEvent createSampleEvent (LocalDateTime recordedAt) {
        AuditEvent entity = Instancio.create(AuditEvent.class);
        entity.setId(null);
        entity.setTimestamp(toTimestampInMilliseconds(recordedAt));
        return entity;
    }
}
