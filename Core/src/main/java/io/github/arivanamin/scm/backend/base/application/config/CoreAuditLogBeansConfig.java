package io.github.arivanamin.scm.backend.base.application.config;

import io.github.arivanamin.scm.backend.base.application.audit.AuditDataExtractor;
import io.github.arivanamin.scm.backend.base.application.audit.KafkaAuditEventPublisher;
import io.github.arivanamin.scm.backend.base.application.outbox.NoOpAuditOutboxMessageStorage;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventPublisher;
import io.github.arivanamin.scm.backend.base.domain.command.CreateAuditOutboxMessageCommand;
import io.github.arivanamin.scm.backend.base.domain.command.UpdateAuditOutboxMessageStatusCommand;
import io.github.arivanamin.scm.backend.base.domain.outbox.AuditOutboxMessageStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class CoreAuditLogBeansConfig {

    @Bean
    public AuditDataExtractor auditDataExtractor (
        @Value ("${spring.application.name}") String serviceName) {
        return new AuditDataExtractor(serviceName);
    }

    @Bean
    public AuditEventPublisher auditEventPublisher (KafkaTemplate<String, AuditEvent> kafkaTemplate,
                                                    UpdateAuditOutboxMessageStatusCommand command) {
        return new KafkaAuditEventPublisher(kafkaTemplate, command);
    }

    @Bean
    public CreateAuditOutboxMessageCommand createAuditEventCommand (
        AuditOutboxMessageStorage storage) {
        return new CreateAuditOutboxMessageCommand(storage);
    }

    @Bean
    @ConditionalOnMissingBean (AuditOutboxMessageStorage.class)
    public AuditOutboxMessageStorage dummyAuditEventStorage () {
        return new NoOpAuditOutboxMessageStorage();
    }
}
