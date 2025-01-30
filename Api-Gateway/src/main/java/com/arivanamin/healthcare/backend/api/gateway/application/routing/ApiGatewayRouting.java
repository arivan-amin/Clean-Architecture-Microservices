package com.arivanamin.healthcare.backend.api.gateway.application.routing;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static java.lang.System.getenv;

@Configuration
@RequiredArgsConstructor
public class ApiGatewayRouting {

    public static final String EUREKA_HOST = getenv().getOrDefault("EUREKA_HOST", "localhost");

    public static final String EUREKA_URL = "http://%s:8761".formatted(EUREKA_HOST);

    private final RoutingHelper routingHelper;

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
        return routingHelper.createApiRouteForService("patient");
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService("patient");
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService("patient");
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceRoute () {
        return routingHelper.createApiRouteForService("audit");
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService("audit");
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService("audit");
    }
}
