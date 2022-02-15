package com.jb.couponSystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Collections;

/**
 * CORS Configuration defines rules that identify the origins that allow to access to the server
 */
@Configuration
public class CORSConfig {
    @Bean
    public CorsFilter corsFilter(){

        // create new url configuration for browsers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // create new cors configuration
        CorsConfiguration config = new CorsConfiguration();

        // allow to get credentials in cors
        config.setAllowCredentials(true);

        // allow to get from any ip/domain
        config.setAllowedOriginPatterns(Collections.singletonList("*"));

        // allow to get any header
        config.addAllowedHeader("*");

        // allow to get methods
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");

        // allow to return header
       config.addExposedHeader("*");

        // allow to get any route
        source.registerCorsConfiguration("/**",config);

        // return new configuration
        return new CorsFilter(source);
    }
}
