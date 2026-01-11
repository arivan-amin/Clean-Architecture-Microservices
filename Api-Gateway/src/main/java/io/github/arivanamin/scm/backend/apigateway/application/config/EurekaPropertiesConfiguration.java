package io.github.arivanamin.scm.backend.apigateway.application.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties (EurekaProperties.class)
class EurekaPropertiesConfiguration {

}
