package com.zacthompson.backend.controller;

import com.zacthompson.backend.entity.User;
import com.zacthompson.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping()
  public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
    if (principal == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Map<String, Object> attributes = principal.getAttributes();
    String name = attributes.getOrDefault("name", "Unknown").toString();
    String email = attributes.getOrDefault("email", "Unknown").toString();

    Map<String, Object> userInfo = new HashMap<>();
    userInfo.put("name", name);
    userInfo.put("email", email);

    return ResponseEntity.ok(userInfo);
  }

}
