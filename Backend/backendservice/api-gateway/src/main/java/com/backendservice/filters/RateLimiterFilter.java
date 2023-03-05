package com.backendservice.filters;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
 import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.ToString;
import reactor.core.publisher.Mono;

 @Component
public class RateLimiterFilter extends AbstractRateLimiter<RateLimiterFilter.Config> {

    public static final String CONFIGURATION_PROPERTY_NAME = "ratelimiter";
    private final Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);
    @Autowired
    private ProxyManager<String> proxyManager;

    public RateLimiterFilter() {
        super(Config.class, CONFIGURATION_PROPERTY_NAME, null);
    }

    @Validated
    @ToString
    public static class Config {
        @Min(1)
        private int replenishRate;
        @Min(0)
        private int burstCapacity = 0;
        public int getReplenishRate() {
            return replenishRate;
        }
        public RateLimiterFilter.Config setReplenishRate(int replenishRate) {
            this.replenishRate = replenishRate;
            return this;
        }
        public int getBurstCapacity() {
            return burstCapacity;
        }
        public RateLimiterFilter.Config setBurstCapacity(int burstCapacity) {
            this.burstCapacity = burstCapacity;
            return this;
        }
    }
    @NotNull
    public Map<String, String> getHeaders(Config config, long tokensLeft) {
        Map<String, String> headers = new HashMap<>();
        headers.put(RedisRateLimiter.REMAINING_HEADER, String.valueOf(tokensLeft));
        headers.put(RedisRateLimiter.REPLENISH_RATE_HEADER,
                String.valueOf(config.getReplenishRate()));
        headers.put(RedisRateLimiter.BURST_CAPACITY_HEADER,
                String.valueOf(config.getBurstCapacity()));
        return headers;
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String key) {
        Config routeConfig = getConfig().get(routeId);
        if (routeConfig == null) {
            throw new IllegalArgumentException("No Configuration found for route " + routeId);
        }
        logger.info(String.format("Route ID is -> %s", routeId));
         int replenishRate = routeConfig.getReplenishRate();
         int burstCapacity = routeConfig.getBurstCapacity();

        Bucket requestBucket = null;

        requestBucket = proxyManager.builder().build(key, BucketConfiguration.builder()
                .addLimit(Bandwidth.classic(burstCapacity, Refill.intervally(replenishRate, Duration.ofSeconds(20))))
                .build());

        try {
            ConsumptionProbe probe = requestBucket.tryConsumeAndReturnRemaining(1);
            if (probe.isConsumed()) {
                Response response = new Response(true, new HashMap<>());
                return Mono.just(response);
            }
            return Mono.just(new Response(false, getHeaders(routeConfig, 0)));

        } catch (Exception e) {
            logger.error("Rate limiting failed: {} ", e.getMessage());
            throw e;
        }

    }

}
