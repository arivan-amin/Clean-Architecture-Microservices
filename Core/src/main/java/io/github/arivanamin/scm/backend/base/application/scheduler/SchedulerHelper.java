package io.github.arivanamin.scm.backend.base.application.scheduler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class SchedulerHelper {

    public static final String SCHEDULER_CRONJOB = "${scheduler.cronjob}";
}
