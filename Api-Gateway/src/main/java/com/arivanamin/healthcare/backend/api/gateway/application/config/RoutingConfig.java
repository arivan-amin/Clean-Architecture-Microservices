package com.arivanamin.healthcare.backend.api.gateway.application.config;

import com.arivanamin.healthcare.backend.api.gateway.application.routing.RoutingHelper;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class RoutingConfig {

    @Bean
    public RedisRateLimiter rateLimiter () {
        return new RedisRateLimiter(1, 1);
    }

    @Bean
    public KeyResolver ipKeyResolver () {
        // todo 1/31/25 - later when security is added, keyResolver should depend on user ID
        return exchange -> Mono.just(exchange.getRequest()
            .getRemoteAddress()
            .getAddress()
            .getHostAddress());
    }

    @Bean
    public RoutingHelper routingHelper (RedisRateLimiter rateLimiter, KeyResolver keyResolver) {
        return new RoutingHelper(rateLimiter, keyResolver);
    }
}
