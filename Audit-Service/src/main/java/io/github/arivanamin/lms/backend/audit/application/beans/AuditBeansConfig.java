package io.github.arivanamin.lms.backend.audit.application.beans;

import io.github.arivanamin.lms.backend.audit.domain.command.create.CreateAuditEventCommand;
import io.github.arivanamin.lms.backend.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.audit.domain.query.readbyid.ReadAuditEventByIdQuery;
import io.github.arivanamin.lms.backend.audit.domain.query.readbyspec.ReadAuditEventsQuery;
import io.github.arivanamin.lms.backend.audit.storage.AuditEventRepository;
import io.github.arivanamin.lms.backend.audit.storage.JpaAuditEventStorage;
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
