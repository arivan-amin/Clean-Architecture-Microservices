package io.github.arivanamin.lms.backend.core.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class ClockConfiguration {

    @Bean
    public Clock clock () {
        return Clock.systemUTC();
    }
}
