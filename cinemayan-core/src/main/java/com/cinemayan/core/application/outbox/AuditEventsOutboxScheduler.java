package com.cinemayan.core.application.outbox;

import com.cinemayan.core.domain.audit.AuditEvent;
import com.cinemayan.core.domain.audit.AuditEventPublisher;
import com.cinemayan.core.domain.command.delete.DeleteCompletedAuditOutboxMessagesCommand;
import com.cinemayan.core.domain.outbox.OutboxMessageStatus;
import com.cinemayan.core.domain.query.readbystatus.ReadAuditOutboxMessageByStatusInput;
import com.cinemayan.core.domain.query.readbystatus.ReadAuditOutboxMessageByStatusQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static com.cinemayan.core.application.scheduler.SchedulerHelper.SCHEDULER_CRONJOB;
import static com.cinemayan.core.domain.topics.AuditTopics.API_AUDIT_TOPIC;

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
            query.execute(new ReadAuditOutboxMessageByStatusInput(OutboxMessageStatus.PENDING))
                .getEvents();
        log.info("Pending auditEvents to be published = {}", auditEvents.size());
        auditEvents.forEach(event -> publisher.sendAuditLog(API_AUDIT_TOPIC, event));
    }

    @Scheduled (cron = SCHEDULER_CRONJOB)
    void deleteCompletedEvents () {
        List<AuditEvent> completedEvents =
            query.execute(new ReadAuditOutboxMessageByStatusInput(OutboxMessageStatus.COMPLETED))
                .getEvents();
        log.info("Completed events to be deleted = {}", completedEvents.size());
        deleteCommand.execute();
    }
}
