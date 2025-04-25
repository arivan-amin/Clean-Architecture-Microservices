package io.github.arivanamin.scm.backend.base.domain.audit;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    long timestamp;
    long duration;
    String response;
}
