package io.github.arivanamin.scm.backend.apigateway.application.routing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static io.github.arivanamin.scm.backend.base.domain.config.ServicesNamesHelper.*;

@Configuration
@RequiredArgsConstructor
public class ApiGatewayRouting {

    @Value ("${EUREKA_HOST:localhost}")
    private static String eurekaHost;

    @Value ("${EUREKA_PORT:8761}")
    private static String eurekaPort;

    public static final String EUREKA_URL = "http://%s:%s".formatted(eurekaHost, eurekaPort);

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
            .route(getNotificationServiceRoute())
            .route(getNotificationServiceApiDocRoute())
            .route(getNotificationServiceActuatorRoute())
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

    private Function<PredicateSpec, Buildable<Route>> getNotificationServiceRoute () {
        return routingHelper.createApiRouteForService(NOTIFICATION_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getNotificationServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService(NOTIFICATION_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getNotificationServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService(NOTIFICATION_SERVICE);
    }
}
