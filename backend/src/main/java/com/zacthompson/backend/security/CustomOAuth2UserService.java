package com.zacthompson.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService implements OAuth2UserService {

  private final String allowedDomain;
  private final String adminEmail;

  private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

  public CustomOAuth2UserService(
          @Value("${auth.allowed.domain}") String allowedDomain,
          @Value("${auth.admin.email}") String adminEmail
  ) {
    this.allowedDomain = allowedDomain;
    this.adminEmail = adminEmail;
  }

  // Implement the method to load user details from OAuth2 provider
  // This method will check the user's email domain and set roles accordingly
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String email = oAuth2User.getAttribute("email");

    if (email == null || (
            !email.toLowerCase().endsWith("@" + allowedDomain.toLowerCase()) &&
                    !email.equalsIgnoreCase(adminEmail))
    ) {
      throw new OAuth2AuthenticationException("Unauthorized email domain.");
    }

    return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
            oAuth2User.getAttributes(),
            "email"
    );
  }
}
