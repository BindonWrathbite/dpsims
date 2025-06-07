package com.zacthompson.backend.security;

import com.zacthompson.backend.entity.User;
import com.zacthompson.backend.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.Optional;

//@Service // Required to register this class as a "Service" Spring bean
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final String adminEmail;
    private final String allowedDomain;

    public CustomOAuth2UserService(UserRepository userRepository, String adminEmail, String allowedDomain) {
        this.userRepository = userRepository;
        this.adminEmail = adminEmail;
        this.allowedDomain = allowedDomain;
        System.out.println("✅ CustomOAuth2UserService manually injected and active");
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("👋 CustomOAuth2UserService is active");
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

        System.out.println("OAuth login attempt with email: " + email);
        System.out.println("Allowed domain: " + allowedDomain);
        System.out.println("Admin email: " + adminEmail);  // delete this line in production

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
