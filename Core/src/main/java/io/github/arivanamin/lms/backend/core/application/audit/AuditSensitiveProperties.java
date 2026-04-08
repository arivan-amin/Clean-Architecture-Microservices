package io.github.arivanamin.lms.backend.core.application.audit;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties (prefix = "audit.masking.sensitive")
@Value
class AuditSensitiveProperties {

    Set<String> fields;
}
