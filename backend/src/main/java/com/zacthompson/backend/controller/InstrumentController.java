package com.zacthompson.backend.controller;

import com.zacthompson.backend.dto.InstrumentDto;
import com.zacthompson.backend.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
  REST controller for managing instruments.
  Uses a single endpoint for flexible filtering and sorting.
 */
@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

  private final InstrumentService instrumentService;

  @Autowired
  public InstrumentController(InstrumentService instrumentService) {
    this.instrumentService = instrumentService;
  }

  /*
    Unified endpoint for retrieving instruments.
    Supports optional filters: type, location, condition, brand, assignedStudent
    Example: GET /api/instruments?type=Trumpet&location=Band Room&sortBy=brand&direction=asc
  */
  @GetMapping
  public ResponseEntity<List<InstrumentDto>> getFilteredInstruments(
          @RequestParam(required = false) String type,
          @RequestParam(required = false) String location,
          @RequestParam(required = false) String condition,
          @RequestParam(required = false) String brand,
          @RequestParam(required = false) String assignedStudent,
          @RequestParam(defaultValue = "type") String sortBy,
          @RequestParam(defaultValue = "asc") String direction
  ) {
    Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
    List<InstrumentDto> results = instrumentService.getFilteredInstruments(
            type, location, condition, brand, assignedStudent, sort
    );
    return ResponseEntity.ok(results);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InstrumentDto> getInstrumentById(@PathVariable Long id) {
    return ResponseEntity.ok(instrumentService.getInstrumentById(id));
  }

  @PostMapping
  public ResponseEntity<InstrumentDto> createInstrument(@RequestBody InstrumentDto dto) {
    return ResponseEntity.ok(instrumentService.createInstrument(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<InstrumentDto> updateInstrument(@PathVariable Long id, @RequestBody InstrumentDto dto) {
    return ResponseEntity.ok(instrumentService.updateInstrument(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInstrument(@PathVariable Long id) {
    instrumentService.deleteInstrument(id);
    return ResponseEntity.noContent().build();
  }
}
