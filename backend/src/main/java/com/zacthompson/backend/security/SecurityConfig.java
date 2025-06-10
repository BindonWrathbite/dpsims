package com.zacthompson.backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  // Define the security filter chain for the application
      return
        http
            .authorizeHttpRequests((auth -> auth  // Start configuring authorization rules
                .requestMatchers("/").permitAll() // Allow unauthenticated access to login and logout endpoints
                .anyRequest().authenticated())) // Require authentication for all other requests
            .oauth2Login(Customizer.withDefaults())
            .build();  // Build and return the security filter chain
    }
}
