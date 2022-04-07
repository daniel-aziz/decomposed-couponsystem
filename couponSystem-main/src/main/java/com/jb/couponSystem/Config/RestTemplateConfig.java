package com.jb.couponSystem.Config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * RestTemplate Configuration
 */
@Configuration
public class RestTemplateConfig {
    private final int CONNECTION_TIME_OUT = 3000; // millis
    private final int READ_TIME_OUT = 3000; // millis

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                //set connection time out for 3 sec
                .setConnectTimeout(Duration.ofMillis(CONNECTION_TIME_OUT))
                //set read time out for 3 sec.
                .setReadTimeout(Duration.ofMillis(READ_TIME_OUT))
                .build();
    }
}

