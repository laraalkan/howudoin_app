package com.example.demo.config; // Replace with your actual package name

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Apply CORS settings to all endpoints
                        .allowedOrigins("*")  // Allow requests from any origin
                        .allowedMethods("*"); // Allow all HTTP methods (GET, POST, PUT, etc.)
            }
        };
    }
}
