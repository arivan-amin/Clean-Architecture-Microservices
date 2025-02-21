package com.arivanamin.healthcare.backend.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static com.arivanamin.healthcare.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@EnableCaching
public class SsoApplication {

    public static void main (String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }
}
