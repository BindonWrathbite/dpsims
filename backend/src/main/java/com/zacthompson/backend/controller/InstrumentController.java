package com.zacthompson.backend.controller;

import com.zacthompson.backend.dto.InstrumentDto;
import com.zacthompson.backend.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for instruments.
 * Supports paginated, sorted, and filtered retrieval of instruments.
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
    Main endpoint for retrieving instruments with optional filters, sorting, and pagination.

    Example:
    GET /api/instruments?type=Trumpet&condition=GOOD&page=1&size=10&sortBy=brand&direction=asc
   */
  @GetMapping
  public ResponseEntity<Page<InstrumentDto>> getFilteredInstruments(
          @RequestParam(required = false) String type,
          @RequestParam(required = false) String location,
          @RequestParam(required = false) String condition,
          @RequestParam(required = false) String brand,
          @RequestParam(required = false) String assignedStudent,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "25") int size,
          @RequestParam(defaultValue = "type") String sortBy,
          @RequestParam(defaultValue = "asc") String direction
  ) {
    Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<InstrumentDto> result = instrumentService.getFilteredInstruments(
            type, location, condition, brand, assignedStudent, pageable
    );
    return ResponseEntity.ok(result);
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
