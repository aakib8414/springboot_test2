package com.test.test1.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DBHealth implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("DB Service","DB Service is ruuning").build();
    }
}
