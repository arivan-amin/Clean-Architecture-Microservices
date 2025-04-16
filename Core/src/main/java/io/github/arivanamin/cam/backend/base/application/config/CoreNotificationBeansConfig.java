package io.github.arivanamin.cam.backend.base.application.config;

import io.github.arivanamin.cam.backend.base.application.notification.KafkaNotificationPublisher;
import io.github.arivanamin.cam.backend.base.domain.notification.NotificationPublisher;
import io.github.arivanamin.cam.backend.base.domain.notification.NotificationRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class CoreNotificationBeansConfig {

    @Bean
    public NotificationPublisher notificationPublisher (
        KafkaTemplate<String, NotificationRequest> kafkaTemplate) {
        return new KafkaNotificationPublisher(kafkaTemplate);
    }
}
