package io.github.arivanamin.lms.backend.base.application.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties (OpenApiServerProperties.class)
class OpenApiServerPropertiesConfiguration {

}
