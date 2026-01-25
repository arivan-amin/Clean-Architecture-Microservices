package io.github.arivanamin.lms.backend.discovery.application.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties (EurekaCredentialProperties.class)
class EurekaCredentialPropertiesConfiguration {

}
