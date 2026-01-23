package io.github.arivanamin.lms.backend.base.application.scheduler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public enum SchedulerHelper {
    ;

    public static final String SCHEDULER_CRONJOB = "${scheduler.cronjob}";
}
