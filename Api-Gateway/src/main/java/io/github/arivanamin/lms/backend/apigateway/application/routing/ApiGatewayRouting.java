package io.github.arivanamin.lms.backend.apigateway.application.routing;

import io.github.arivanamin.lms.backend.apigateway.application.config.EurekaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static io.github.arivanamin.lms.backend.base.core.config.ServicesNamesHelper.AUDIT_SERVICE;
import static io.github.arivanamin.lms.backend.base.core.config.ServicesNamesHelper.STUDENT_SERVICE;

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
            .route(getStudentServiceRoute())
            .route(getStudentServiceApiDocRoute())
            .route(getStudentServiceActuatorRoute())
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

    private Function<PredicateSpec, Buildable<Route>> getStudentServiceRoute () {
        return routingHelper.createApiRouteForService(STUDENT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getStudentServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService(STUDENT_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getStudentServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService(STUDENT_SERVICE);
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
