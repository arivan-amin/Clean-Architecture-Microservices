package io.github.arivanamin.cam.backend.audit.application.beans;

import io.github.arivanamin.cam.backend.audit.core.command.CreateAuditEventCommand;
import io.github.arivanamin.cam.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.cam.backend.audit.core.query.*;
import io.github.arivanamin.cam.backend.audit.storage.AuditEventRepository;
import io.github.arivanamin.cam.backend.audit.storage.JpaAuditEventStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class AuditBeansConfig {

    @Bean
    public AuditEventStorage storage (AuditEventRepository repository) {
        return new JpaAuditEventStorage(repository);
    }

    @Bean
    public ReadAuditEventsQuery readAuditEventsQuery (AuditEventStorage storage) {
        return new ReadAuditEventsQuery(storage);
    }

    @Bean
    public ReadAuditEventsByCriteriaQuery readByCriteriaQuery (AuditEventStorage storage) {
        return new ReadAuditEventsByCriteriaQuery(storage);
    }

    @Bean
    public ReadAuditEventByIdQuery readAuditEventByIdQuery (AuditEventStorage storage) {
        return new ReadAuditEventByIdQuery(storage);
    }

    @Bean
    public CreateAuditEventCommand createAuditEventCommand (AuditEventStorage storage) {
        return new CreateAuditEventCommand(storage);
    }
}
