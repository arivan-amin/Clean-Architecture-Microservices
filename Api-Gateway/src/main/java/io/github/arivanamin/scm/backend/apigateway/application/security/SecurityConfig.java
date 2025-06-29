package io.github.arivanamin.scm.backend.apigateway.application.security;

import org.springframework.context.annotation.Configuration;

@Configuration
class SecurityConfig {

    // private final String[] publicResourceUrls =
    //     { "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**",
    //         "/api-docs/**", "/aggregate/**", "/actuator/prometheus" };

    // @Bean
    // public SecurityWebFilterChain securityFilterChain (ServerHttpSecurity httpSecurity) {
    //     return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
    //         .authorizeExchange(authorize -> authorize.pathMatchers(publicResourceUrls)
    //             .permitAll()
    //             .anyExchange()
    //             .permitAll())
    //         .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
    //         .build();
    // }
}
