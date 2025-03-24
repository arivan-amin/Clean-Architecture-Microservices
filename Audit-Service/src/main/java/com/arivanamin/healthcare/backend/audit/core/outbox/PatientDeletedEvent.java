package com.arivanamin.healthcare.backend.audit.core.outbox;

import com.arivanamin.healthcare.backend.base.domain.outbox.OutboxEvent;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDeletedEvent implements OutboxEvent {

    private UUID id;
    private UUID patientId;

    @Override
    public UUID getEventId () {
        return id;
    }
}
