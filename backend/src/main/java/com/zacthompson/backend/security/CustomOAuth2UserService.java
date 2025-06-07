package com.zacthompson.backend.security;

import com.zacthompson.backend.entity.User;
import com.zacthompson.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service // Required to register this class as a "Service" Spring bean
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Value("${auth.admin.email}")  // Admin email for special access
    private String adminEmail;

    @Value("${auth.allowed.domain}")  // Domain that is allowed to authenticate
    private String allowedDomain;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // --- Validate required config values ---
        if (adminEmail == null || adminEmail.isBlank()) {
            throw new IllegalStateException("Admin email is not configured (auth.admin.email)");
        }
        if (allowedDomain == null || allowedDomain.isBlank()) {
            throw new IllegalStateException("Allowed domain is not configured (auth.allowed.domain)");
        }

        // Extract user information from the OAuth2 user
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Enforce domain restriction or allow specific admin email
        if (email == null ||
                (!email.toLowerCase().endsWith("@" + allowedDomain.toLowerCase()) &&
                        (!email.equalsIgnoreCase(adminEmail)))) {
            throw new OAuth2AuthenticationException("Unauthorized email domain");
        }

        // Fetch or Create user in the database
        Optional<User> optionalUser = userRepository.findByEmail(email);  // checks if email exists
        User user = optionalUser.orElseGet(() -> {  // assigns user if exists, otherwise creates a new one
            User newUser = new User();
            newUser.setEmail(email); // Email of user
            newUser.setName(name); // Name of user
            newUser.setRole("USER"); // Default role
            return userRepository.save(newUser); // Save new user to the database
        });

        // Return Spring Security's OAuth2User with custom user details
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}
