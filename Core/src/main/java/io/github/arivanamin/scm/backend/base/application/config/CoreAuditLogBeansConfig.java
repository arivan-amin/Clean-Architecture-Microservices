package io.github.arivanamin.scm.backend.base.application.config;

import io.github.arivanamin.scm.backend.base.application.audit.AuditDataExtractor;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.domain.audit.CreateAuditEventCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class CoreAuditLogBeansConfig {

    @Bean
    public AuditDataExtractor auditDataExtractor (
        @Value ("${spring.application.name}") String serviceName) {
        return new AuditDataExtractor(serviceName);
    }

    @Bean
    public CreateAuditEventCommand createAuditEventCommand (AuditEventStorage storage) {
        return new CreateAuditEventCommand(storage);
    }
}
