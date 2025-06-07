package com.zacthompson.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

  @GetMapping("/api/landing")
  public String landing() {
    return "Successfully reached the landing page!";
  }

  @GetMapping("/")
  public String index() {
    return "Welcome to the backend application!";
  }
}
