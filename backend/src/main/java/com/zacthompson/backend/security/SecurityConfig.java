package com.zacthompson.backend.security;

import com.zacthompson.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService(
            UserRepository userRepository,
            @Value("${auth.admin.email}") String adminEmail,
            @Value("${auth.allowed.domain}") String allowedDomain)
    {
        return new CustomOAuth2UserService(userRepository, adminEmail, allowedDomain);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService
    ) throws Exception {  // Define the security filter chain for the application
        System.out.println("✅ SecurityFilterChain is initializing");

        http
            .authorizeHttpRequests((auth -> auth  // Start configuring authorization rules
                .requestMatchers("/api/auth/login", "/api/auth/logout").permitAll() // Allow unauthenticated access to login and logout endpoints
                .anyRequest().authenticated())) // Require authentication for all other requests
            .oauth2Login(oauth2 -> oauth2  // Configure OAuth2 login
                .userInfoEndpoint(userInfo -> userInfo  // Set up user info endpoint
                    .userService(customOAuth2UserService)))  // Use custom user service for loading user details
            .logout(logout -> logout
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();  // Build and return the security filter chain
    }
}
