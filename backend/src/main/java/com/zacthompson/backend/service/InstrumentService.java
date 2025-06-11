package com.zacthompson.backend.service;

import com.zacthompson.backend.dto.InstrumentDto;
import org.springframework.data.domain.Sort;

import java.util.List;

/*
  Interface defining the service layer for instrument-related operations.
  This decouples the business logic from the controller and allows flexibility in implementation.
 */
public interface InstrumentService {

  // Retrieve all instruments, sorted by a specified field and direction.
  List<InstrumentDto> getAllInstruments(Sort sort);

  // Get a single instrument by its unique ID.
  InstrumentDto getInstrumentById(Long id);

  // Save a new instrument to the database.
  InstrumentDto createInstrument(InstrumentDto dto);

  // Update an existing instrument by ID.
  InstrumentDto updateInstrument(Long id, InstrumentDto dto);

  // Delete an instrument by ID.
  void deleteInstrument(Long id);

  // Get instruments filtered by type, with optional sorting.
  List<InstrumentDto> getInstrumentsByType(String type, Sort sort);

  // Get instruments filtered by type and location, with sorting.
  List<InstrumentDto> getInstrumentsByTypeAndLocation(String type, String location, Sort sort);

  // Get instruments filtered by type and condition, with sorting.
  List<InstrumentDto> getInstrumentsByTypeAndCondition(String type, String condition, Sort sort);
}
