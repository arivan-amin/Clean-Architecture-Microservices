package com.arivanamin.healthcare.backend.api.gateway.application.routing;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

import java.util.function.Function;

@RequiredArgsConstructor
public class RoutingHelper {

    private final RedisRateLimiter rateLimiter;

    private final KeyResolver keyResolver;

    public Function<PredicateSpec, Buildable<Route>> createApiRouteForService (String serviceName) {
        return r -> r.path("/%ss/**".formatted(serviceName))
            .filters(f -> f.requestRateLimiter(config -> config.setRateLimiter(rateLimiter)
                .setKeyResolver(keyResolver)))
            .uri(getLoadBalancedServiceUrl(serviceName));
    }

    private String getLoadBalancedServiceUrl (String serviceName) {
        return "lb://%s-service".formatted(serviceName);
    }

    public Function<PredicateSpec, Buildable<Route>> createApiDocRouteForService (
        String serviceName) {
        return r -> r.path("/%s-service/api-docs".formatted(serviceName))
            .filters(f -> f.setPath("/v3/api-docs"))
            .uri(getLoadBalancedServiceUrl(serviceName));
    }

    public Function<PredicateSpec, Buildable<Route>> createActuatorRouteForService (
        String serviceName) {
        return r -> r.path("/actuator/%ss/**".formatted(serviceName))
            .filters(f -> f.rewritePath("/actuator/%ss/(?<segment>.*)".formatted(serviceName),
                "/actuator/${segment}"))
            .uri(getLoadBalancedServiceUrl(serviceName));
    }
}
