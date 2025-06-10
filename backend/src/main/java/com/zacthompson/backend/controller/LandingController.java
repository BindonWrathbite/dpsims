package com.zacthompson.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class LandingController {

  @GetMapping("")
  public String landing() {
    return "Hello, World!";
  }

  @GetMapping("/secured")
  public String secured() {return "Hello, Secured World!";}  // Endpoint that requires authentication
}
