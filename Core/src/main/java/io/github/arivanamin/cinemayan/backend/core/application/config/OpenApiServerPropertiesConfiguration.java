package io.github.arivanamin.cinemayan.backend.core.application.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties (OpenApiServerProperties.class)
class OpenApiServerPropertiesConfiguration {

}
