package io.github.arivanamin.cinemayan.core.application.audit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties (AuditSensitiveProperties.class)
class AuditSensitivePropertiesConfig {

}
