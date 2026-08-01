package com.mcmanuel.EmailGeneration.project;

import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Service
@Configuration
public class SecurityConfiguration {

    @Bean
    public CorsConfiguration corsConfig(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("https://google.com","http://127.0.0.1:5500/**"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("POST","GET","DELETE"));

        return config;
    }
}
