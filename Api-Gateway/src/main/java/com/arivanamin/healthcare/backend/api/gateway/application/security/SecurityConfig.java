package com.arivanamin.healthcare.backend.api.gateway.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
class SecurityConfig {

    private final String[] publicResourceUrls =
        { "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**",
            "/api-docs/**", "/aggregate/**", "/actuator/prometheus" };

    @Bean
    public SecurityWebFilterChain securityFilterChain (ServerHttpSecurity httpSecurity) {
        return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(
                authorize -> authorize.pathMatchers(publicResourceUrls)
                    .permitAll()
                    .anyExchange()
                    .permitAll())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
            .build();
    }
}
