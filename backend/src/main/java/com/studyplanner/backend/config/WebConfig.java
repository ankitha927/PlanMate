package com.studyplanner.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman/React testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/plans/**", "/api/subjects/**").permitAll() // Allow public access
                .anyRequest().authenticated() // Require auth for other endpoints
            );

        return http.build();
    }
}
