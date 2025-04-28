package io.github.arivanamin.scm.backend.base.application.outbox;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventPublisher;
import io.github.arivanamin.scm.backend.base.domain.audit.outbox.ReadAuditOutboxMessageByStatusQuery;
import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static io.github.arivanamin.scm.backend.base.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuditOutboxPublisherScheduler {

    private final ReadAuditOutboxMessageByStatusQuery query;
    private final AuditEventPublisher publisher;

    @Scheduled (fixedRate = 10_000, initialDelay = 10_000)
    void execute () {
        List<AuditEvent> auditEvents = query.execute(OutboxMessageStatus.PENDING);
        log.info("Pending auditEvents to be published = {}", auditEvents.size());
        auditEvents.forEach(event -> publisher.sendAuditLog(API_AUDIT_TOPIC, event));
    }
}
