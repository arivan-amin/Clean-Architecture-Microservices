package io.github.arivanamin.lms.backend.core.application.outbox;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.audit.AuditEventPublisher;
import io.github.arivanamin.lms.backend.core.domain.command.DeleteCompletedAuditOutboxMessagesCommand;
import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import io.github.arivanamin.lms.backend.core.domain.query.ReadAuditOutboxMessageByStatusQuery;
import io.github.arivanamin.lms.backend.core.domain.query.ReadAuditOutboxMessageByStatusQueryInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static io.github.arivanamin.lms.backend.core.application.scheduler.SchedulerHelper.SCHEDULER_CRONJOB;
import static io.github.arivanamin.lms.backend.core.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Configuration
@RequiredArgsConstructor
@Slf4j
class AuditEventsOutboxScheduler {

    private final AuditEventPublisher publisher;
    private final ReadAuditOutboxMessageByStatusQuery query;
    private final DeleteCompletedAuditOutboxMessagesCommand deleteCommand;

    @Scheduled (cron = SCHEDULER_CRONJOB)
    void sendEvents () {
        List<AuditEvent> auditEvents =
            query.execute(new ReadAuditOutboxMessageByStatusQueryInput(OutboxMessageStatus.PENDING))
                .getEvents();
        log.info("Pending auditEvents to be published = {}", auditEvents.size());
        auditEvents.forEach(event -> publisher.sendAuditLog(API_AUDIT_TOPIC, event));
    }

    @Scheduled (cron = SCHEDULER_CRONJOB)
    void deleteCompletedEvents () {
        List<AuditEvent> completedEvents = query.execute(
                new ReadAuditOutboxMessageByStatusQueryInput(OutboxMessageStatus.COMPLETED))
            .getEvents();
        log.info("Completed events to be deleted = {}", completedEvents.size());
        deleteCommand.execute();
    }
}
