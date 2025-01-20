package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Disable password encryption for testing purposes
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); // No password encoding
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (testing only, enable in production)
                .authorizeRequests(authz -> authz
                        .anyRequest().permitAll()) // Allow all requests without authentication
                .formLogin(form -> form.disable()) // Disable form login
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic Auth

        return http.build();
    }
}
/*package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password encoder bean to be used for encoding passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }

    // Configure the HTTP security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security using the builder pattern
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (for testing purposes, adjust as needed)
                .authorizeRequests(authz -> authz
                        .requestMatchers("/**").permitAll()  // Permit all endpoints (open access to all URLs for testing)
                        .anyRequest().authenticated())  // Require authentication for any other requests
                .formLogin(form -> form.disable())  // Disable form-based login (adjust based on your needs)
                .httpBasic(httpBasic -> httpBasic.disable());  // Disable HTTP Basic authentication (adjust based on your needs)

        return http.build();
      }
}*/
