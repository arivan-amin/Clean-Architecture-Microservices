package com.cinemayan.apigateway.application.config;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ToString
public class ServiceRoute {

    private String name;
    private String pathPrefix;

    private boolean actuatorEnabled;
    private boolean apiDocEnabled;
    private boolean rateLimiterEnabled;
}
