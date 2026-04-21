package io.github.arivanamin.cinemayan.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static io.github.arivanamin.cinemayan.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableEurekaServer
public class DiscoveryServerApplication {

    static void main (String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}
