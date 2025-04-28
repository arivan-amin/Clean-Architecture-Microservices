package io.github.arivanamin.scm.backend.common.application.config;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventStorage;
import io.github.arivanamin.scm.backend.common.storage.audit.AuditEventRepository;
import io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditEventStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class StorageCommonBeansConfig {

    @Bean
    public AuditEventStorage auditEventStorage (AuditEventRepository repository) {
        return new JpaAuditEventStorage(repository);
    }
}
