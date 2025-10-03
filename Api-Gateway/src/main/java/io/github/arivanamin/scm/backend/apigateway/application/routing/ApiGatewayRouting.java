package io.github.arivanamin.scm.backend.apigateway.application.routing;

import io.github.arivanamin.scm.backend.apigateway.application.config.EurekaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static io.github.arivanamin.scm.backend.base.core.config.ServicesNamesHelper.AUDIT_SERVICE;
import static io.github.arivanamin.scm.backend.base.core.config.ServicesNamesHelper.PATIENT_SERVICE;

@Slf4j
@Configuration
@RequiredArgsConstructor
class ApiGatewayRouting {

    private final RoutingHelper routingHelper;
    private final EurekaProperties eurekaProperties;

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

    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerStaticResourcesRoute () {
        return r -> r.path("/eureka/**")
            .uri(getEurekaUrl());
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

    private String getEurekaUrl () {
        return "http://%s:%s".formatted(eurekaProperties.host(), eurekaProperties.port());
    }
}
