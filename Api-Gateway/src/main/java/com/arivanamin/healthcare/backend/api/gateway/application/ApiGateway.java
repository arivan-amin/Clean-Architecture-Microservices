package com.arivanamin.healthcare.backend.api.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static com.arivanamin.healthcare.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableDiscoveryClient
public class ApiGateway {

    public static void main (String[] args) {
        SpringApplication.run(ApiGateway.class, args);
    }
}
