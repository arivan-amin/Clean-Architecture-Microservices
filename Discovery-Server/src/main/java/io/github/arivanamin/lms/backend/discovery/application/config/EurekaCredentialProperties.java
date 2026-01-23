package io.github.arivanamin.lms.backend.discovery.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "lms.eureka.credentials")
public record EurekaCredentialProperties(String username, String password) {

}
