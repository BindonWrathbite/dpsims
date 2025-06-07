package com.zacthompson.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class AuthController {

  @GetMapping("/logout-success")
  public ResponseEntity<String> logoutSuccess() {
    return ResponseEntity.ok("👋 You’ve been logged out successfully.");
  }

  @GetMapping("/success")
  public ResponseEntity<String> loginSuccess(@AuthenticationPrincipal OAuth2User principal) {
    String email = principal.getAttribute("email");
    return ResponseEntity.ok("✅ Login successful! Welcome, " + email);
  }

  @GetMapping("/login")
  public ResponseEntity<String> loginPrompt() {
    return ResponseEntity.ok("🔐 Please log in with Google at /oauth2/authorization/google");
  }
}
