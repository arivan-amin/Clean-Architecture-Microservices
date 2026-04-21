package io.github.arivanamin.cinemayan.audit.application.beans;

import io.github.arivanamin.cinemayan.audit.domain.command.create.CreateAuditEventCommand;
import io.github.arivanamin.cinemayan.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.cinemayan.audit.domain.query.readbyid.ReadAuditEventByIdQuery;
import io.github.arivanamin.cinemayan.audit.domain.query.readbyspec.ReadAuditEventsQuery;
import io.github.arivanamin.cinemayan.audit.infrastructure.AuditEventRepository;
import io.github.arivanamin.cinemayan.audit.infrastructure.JpaAuditEventStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AuditBeansConfig {

    @Bean
    public AuditEventStorage auditEventStorage (AuditEventRepository repository) {
        return new JpaAuditEventStorage(repository);
    }

    @Bean
    public ReadAuditEventsQuery readAuditEventsQuery (AuditEventStorage storage) {
        return new ReadAuditEventsQuery(storage);
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
