package io.github.arivanamin.lms.backend.core.application.audit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties (AuditSensitiveProperties.class)
class AuditSensitivePropertiesConfig {

}
