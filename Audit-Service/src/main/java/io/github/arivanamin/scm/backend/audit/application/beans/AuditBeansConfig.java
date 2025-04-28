package io.github.arivanamin.scm.backend.audit.application.beans;

import io.github.arivanamin.scm.backend.audit.core.query.*;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventStorage;
import io.github.arivanamin.scm.backend.common.storage.audit.AuditEventRepository;
import io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditEventStorage;
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
}
