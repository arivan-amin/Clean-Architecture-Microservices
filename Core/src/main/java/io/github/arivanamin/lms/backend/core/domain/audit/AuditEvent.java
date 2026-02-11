package io.github.arivanamin.lms.backend.core.domain.audit;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class AuditEvent {

    UUID id;
    String serviceName;
    String location;
    String action;
    String data;
    Instant recordedAt;
    Duration duration;
    String response;
}
