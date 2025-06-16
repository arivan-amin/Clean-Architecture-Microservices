package io.github.arivanamin.scm.backend.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import static io.github.arivanamin.scm.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableJpaRepositories (basePackages = BASE_PACKAGE)
@EntityScan (basePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
public class AuditApplication {

    public static void main (String[] args) {
        SpringApplication.run(AuditApplication.class, args);
    }
}
