package com.arivanamin.healthcare.backend.base.application.config;

import com.arivanamin.healthcare.backend.base.application.notification.KafkaNotificationPublisher;
import com.arivanamin.healthcare.backend.base.domain.notification.NotificationPublisher;
import com.arivanamin.healthcare.backend.base.domain.notification.NotificationRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class NotificationBeansConfig {

    @Bean
    public NotificationPublisher publisher (
        KafkaTemplate<String, NotificationRequest> kafkaTemplate) {
        return new KafkaNotificationPublisher(kafkaTemplate);
    }
}
