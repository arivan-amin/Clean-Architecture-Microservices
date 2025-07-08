package io.github.arivanamin.scm.backend.base.application.outbox;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventPublisher;
import io.github.arivanamin.scm.backend.base.domain.command.DeleteCompletedAuditOutboxMessagesCommand;
import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import io.github.arivanamin.scm.backend.base.domain.query.ReadAuditOutboxMessageByStatusQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static io.github.arivanamin.scm.backend.base.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuditEventsOutboxScheduler {

    public static final int SCHEDULED_TIMING = 20_000;

    private final AuditEventPublisher publisher;
    private final ReadAuditOutboxMessageByStatusQuery query;
    private final DeleteCompletedAuditOutboxMessagesCommand deleteCommand;

    @Scheduled (fixedRate = SCHEDULED_TIMING, initialDelay = SCHEDULED_TIMING)
    void sendEvents () {
        List<AuditEvent> auditEvents = query.execute(OutboxMessageStatus.PENDING);
        log.info("Pending auditEvents to be published = {}", auditEvents.size());
        auditEvents.forEach(event -> publisher.sendAuditLog(API_AUDIT_TOPIC, event));
    }

    @Scheduled (fixedRate = SCHEDULED_TIMING, initialDelay = SCHEDULED_TIMING)
    void deleteCompletedEvents () {
        List<AuditEvent> completedEvents = query.execute(OutboxMessageStatus.COMPLETED);
        log.info("Completed events to be deleted = {}", completedEvents.size());
        deleteCommand.execute();
    }
}
