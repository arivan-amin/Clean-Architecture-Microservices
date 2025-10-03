package io.github.arivanamin.scm.backend.apigateway;

import io.github.arivanamin.scm.backend.apigateway.application.config.EurekaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static io.github.arivanamin.scm.backend.base.core.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@EnableConfigurationProperties (EurekaProperties.class)
public class ApiGatewayApplication {

    public static void main (String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
