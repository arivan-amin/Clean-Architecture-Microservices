package io.github.arivanamin.cinemayan.backend.core.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "lms.openapi.server.url")
public record OpenApiServerProperties(String url) {

}
