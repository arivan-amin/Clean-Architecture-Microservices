package io.github.arivanamin.scm.backend.base.application.notification;

import io.github.arivanamin.scm.backend.base.domain.notification.NotificationPublisher;
import io.github.arivanamin.scm.backend.base.domain.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
public class KafkaNotificationPublisher implements NotificationPublisher {

    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;

    @Override
    public void sendNotification (String topic, NotificationRequest request) {
        var completable = kafkaTemplate.send(topic, request);

        completable.whenComplete((result, throwable) -> {
            String formattedResult = result == null ? "" : result.toString();
            String formattedThrowable = throwable == null ? "" : throwable.getMessage();
            log.info("published notification request with result= {}, and throwable = {}",
                formattedResult, formattedThrowable);
        });
    }
}
