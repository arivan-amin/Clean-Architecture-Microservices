package io.github.arivanamin.scm.backend.base.application.outbox;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.core.audit.AuditEventPublisher;
import io.github.arivanamin.scm.backend.base.core.command.DeleteCompletedAuditOutboxMessagesCommand;
import io.github.arivanamin.scm.backend.base.core.outbox.OutboxMessageStatus;
import io.github.arivanamin.scm.backend.base.core.query.ReadAuditOutboxMessageByStatusQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static io.github.arivanamin.scm.backend.base.application.scheduler.SchedulerHelper.SCHEDULER_CRONJOB;
import static io.github.arivanamin.scm.backend.base.core.topics.AuditTopics.API_AUDIT_TOPIC;

@Configuration
@RequiredArgsConstructor
@Slf4j
class AuditEventsOutboxScheduler {

    private final AuditEventPublisher publisher;
    private final ReadAuditOutboxMessageByStatusQuery query;
    private final DeleteCompletedAuditOutboxMessagesCommand deleteCommand;

    @Scheduled (cron = SCHEDULER_CRONJOB)
    void sendEvents () {
        List<AuditEvent> auditEvents = query.execute(OutboxMessageStatus.PENDING);
        log.info("Pending auditEvents to be published = {}", auditEvents.size());
        auditEvents.forEach(event -> publisher.sendAuditLog(API_AUDIT_TOPIC, event));
    }

    @Scheduled (cron = SCHEDULER_CRONJOB)
    void deleteCompletedEvents () {
        List<AuditEvent> completedEvents = query.execute(OutboxMessageStatus.COMPLETED);
        log.info("Completed events to be deleted = {}", completedEvents.size());
        deleteCommand.execute();
    }
}
