package com.arivanamin.healthcare.backend.discovery.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static com.arivanamin.healthcare.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableEurekaServer
public class DiscoveryServer {

    public static void main (String[] args) {
        SpringApplication.run(DiscoveryServer.class, args);
    }
}
