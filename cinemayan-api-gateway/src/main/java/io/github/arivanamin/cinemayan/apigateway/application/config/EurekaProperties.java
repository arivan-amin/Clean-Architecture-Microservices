package io.github.arivanamin.cinemayan.apigateway.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "lms.eureka")
public record EurekaProperties(String host, String port) {

}
