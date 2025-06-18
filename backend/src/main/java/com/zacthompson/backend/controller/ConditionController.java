package com.zacthompson.backend.controller;

import com.zacthompson.backend.entity.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/*
  Controller to expose Condition enum values for frontend use.
 */
@RestController
@RequestMapping("/api/conditions")
public class ConditionController {

  @GetMapping
  public ResponseEntity<List<Condition>> getAllConditions() {
    return ResponseEntity.ok(Arrays.asList(Condition.values()));
  }
}
