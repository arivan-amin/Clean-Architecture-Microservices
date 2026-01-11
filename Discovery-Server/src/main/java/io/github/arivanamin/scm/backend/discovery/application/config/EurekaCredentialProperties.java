package io.github.arivanamin.scm.backend.discovery.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "scm.eureka.credentials")
public record EurekaCredentialProperties(String username, String password) {

}
