package com.mcmanuel.EmailGeneration.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Service
@Configuration
public class SecurityConfiguration {

    @Bean
    public CorsFilter corsConfig(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("https://google.com","http://127.0.0.1:5500/src/main/resources/static/**"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("POST","GET","DELETE"));

        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
}
