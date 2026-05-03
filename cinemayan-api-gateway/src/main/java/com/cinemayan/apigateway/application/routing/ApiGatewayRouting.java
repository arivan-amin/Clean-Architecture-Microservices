package com.cinemayan.apigateway.application.routing;

import com.cinemayan.apigateway.application.config.EurekaProperties;
import com.cinemayan.apigateway.application.config.RouteProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static com.cinemayan.core.domain.config.ServicesNamesHelper.AUDIT_SERVICE;
import static com.cinemayan.core.domain.config.ServicesNamesHelper.CATALOG_SERVICE;

@Slf4j
@Configuration
@RequiredArgsConstructor
class ApiGatewayRouting {

    private final RoutingHelper routingHelper;
    private final EurekaProperties eurekaProperties;
    private final RouteProperties routeProperties;

    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder) {
        log.info("config routeProperties = {}", routeProperties);
        return builder.routes()
            .route(getDiscoveryServerRoute())
            .route(getDiscoveryServerStaticResourcesRoute())
            .route(getCatalogServiceRoute())
            .route(getCatalogServiceApiDocRoute())
            .route(getCatalogServiceActuatorRoute())
            .route(getAuditServiceRoute())
            .route(getAuditServiceApiDocRoute())
            .route(getAuditServiceActuatorRoute())
            .build();
    }

    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerRoute () {
        return r -> r.path("/eureka/web")
            .filters(filter -> filter.setPath("/"))
            .uri(getEurekaUrl());
    }

    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerStaticResourcesRoute () {
        return r -> r.path("/eureka/**")
            .uri(getEurekaUrl());
    }

    private Function<PredicateSpec, Buildable<Route>> getCatalogServiceRoute () {
        return routingHelper.createApiRouteForService(CATALOG_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getCatalogServiceApiDocRoute () {
        return routingHelper.createApiDocRouteForService(CATALOG_SERVICE);
    }

    private Function<PredicateSpec, Buildable<Route>> getCatalogServiceActuatorRoute () {
        return routingHelper.createActuatorRouteForService(CATALOG_SERVICE);
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
