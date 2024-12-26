package com.example.metrics.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;
import java.util.Map;
@Data
@Configuration
@EnableRetry  // 启用 Spring Retry
@ConfigurationProperties(prefix = "retry")
public class RetryConfig {

    private boolean enabled;
    private int maxAttempts;
    private long initialInterval;
    private double multiplier;
    private long maxInterval;
    private List<String> exceptions;

    // Getters and setters for all fields

    @Bean
    public RetryTemplate retryTemplate() {
        // 定义一个 RetryPolicy，设置最大重试次数
        RetryPolicy retryPolicy = new SimpleRetryPolicy(maxAttempts,
                Map.of(java.net.SocketTimeoutException.class, true));

        // 定义一个 BackOffPolicy，设置重试的间隔和倍数
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialInterval);
        backOffPolicy.setMultiplier(multiplier);
        backOffPolicy.setMaxInterval(maxInterval);

        // 创建 RetryTemplate
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }

}

