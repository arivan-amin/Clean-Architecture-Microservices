package io.github.arivanamin.lms.backend.base.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "lms.openapi.server.url")
public record OpenApiServerProperties(String url) {

}
