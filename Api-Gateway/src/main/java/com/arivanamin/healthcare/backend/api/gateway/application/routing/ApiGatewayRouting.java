package com.arivanamin.healthcare.backend.api.gateway.application.routing;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static java.lang.System.getenv;

@Configuration
public class ApiGatewayRouting {

    public static final String EUREKA_HOST = getenv().getOrDefault("EUREKA_HOST", "localhost");

    public static final String EUREKA_URL = "http://%s:8761".formatted(EUREKA_HOST);

    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder) {
        return builder.routes()
            .route(getDiscoveryServerRoute())
            .route(getDiscoveryServerStaticResourcesRoute())
            .route(getPatientServiceRoute())
            .route(getPatientServiceApiDocRoute())
            .route(getPatientServiceActuatorRoute())
            .route(getAuditServiceRoute())
            .route(getAuditServiceApiDocRoute())
            .route(getAuditServiceActuatorRoute())
            .build();
    }

    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerRoute () {
        return r -> r.path("/eureka/web")
            .filters(f -> f.setPath("/"))
            .uri(EUREKA_URL);
    }

    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerStaticResourcesRoute () {
        return r -> r.path("/eureka/**")
            .uri(EUREKA_URL);
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceRoute () {
        return r -> r.path("/patients/**")
            .uri("lb://patient-service");
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceApiDocRoute () {
        return r -> r.path("/patient-service/api-docs")
            .filters(f -> f.setPath("/v3/api-docs"))
            .uri("lb://patient-service");
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceActuatorRoute () {
        return r -> r.path("/actuator/patients/**")
            .filters(
                f -> f.rewritePath("/actuator/patients/(?<segment>.*)", "/actuator/${segment}"))
            .uri("lb://patient-service");
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceRoute () {
        return r -> r.path("/audits/**")
            .uri("lb://audit-service");
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceApiDocRoute () {
        return r -> r.path("/audit-service/api-docs")
            .filters(f -> f.setPath("/v3/api-docs"))
            .uri("lb://audit-service");
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceActuatorRoute () {
        return r -> r.path("/actuator/audits/**")
            .filters(f -> f.rewritePath("/actuator/audits/(?<segment>.*)", "/actuator/${segment}"))
            .uri("lb://audit-service");
    }
}
