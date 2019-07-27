package ru.ifmo.ctddev.tenischev.news.publisher.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

/**
 * Simple health check service provider.
 */
@Health
@ApplicationScoped
public class ServiceHealthCheck implements HealthCheck {

    /**
     * Health check method provider.
     * 
     * @return health status
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named(ServiceHealthCheck.class.getSimpleName()).up().build();
    }
}
