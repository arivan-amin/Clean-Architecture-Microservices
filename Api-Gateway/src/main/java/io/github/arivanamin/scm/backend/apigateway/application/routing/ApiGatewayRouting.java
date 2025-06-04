package io.github.arivanamin.scm.backend.apigateway.application.routing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static io.github.arivanamin.scm.backend.base.domain.config.ServicesNamesHelper.AUDIT_SERVICE;
import static io.github.arivanamin.scm.backend.base.domain.config.ServicesNamesHelper.PATIENT_SERVICE;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApiGatewayRouting {

    private final RoutingHelper routingHelper;

    @Value ("${EUREKA_HOST:localhost}")
    private String eurekaHost;

    @Value ("${EUREKA_PORT:8761}")
    private String eurekaPort;

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
            .uri(getEurekaUrl());
    }

    private String getEurekaUrl () {
        return "http://%s:%s".formatted(eurekaHost, eurekaPort);
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceRoute () {
        return routingHelper.createApiRouteForService(PATIENT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService(PATIENT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getPatientServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService(PATIENT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceRoute () {
        return routingHelper.createApiRouteForService(AUDIT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService(AUDIT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getAuditServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService(AUDIT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerStaticResourcesRoute () {
        return r -> r.path("/eureka/**")
            .uri(getEurekaUrl());
    }
}
