package io.github.arivanamin.lms.backend.audit.core.command.create;

import io.github.arivanamin.lms.backend.audit.core.persistence.AuditEventStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class CreateAuditEventCommand {

    private final AuditEventStorage storage;

    public CreateAuditEventCommandOutput execute (CreateAuditEventCommandInput input) {
        UUID id = storage.create(input.getEvent());
        return new CreateAuditEventCommandOutput(id);
    }
}
