package com.arivanamin.healthcare.backend.base.domain.outbox;

import java.util.UUID;

public interface OutboxEvent {

    UUID getEventId ();
}
