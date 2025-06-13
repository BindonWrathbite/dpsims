package com.zacthompson.backend.service;

import com.zacthompson.backend.dto.InstrumentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface InstrumentService {

  // Get all instruments, sorted (used for non-paginated views or exports)
  List<InstrumentDto> getAllInstruments(Sort sort);

  // Get one instrument by ID
  InstrumentDto getInstrumentById(Long id);

  // Create a new instrument
  InstrumentDto createInstrument(InstrumentDto dto);

  // Create multiple instruments in bulk
  List<InstrumentDto> createInstruments(List<InstrumentDto> dtos);

  // Update an existing instrument by ID
  InstrumentDto updateInstrument(Long id, InstrumentDto dto);

  // Delete an instrument by ID
  void deleteInstrument(Long id);

  // Get a page of instruments filtered by optional fields (type, location, condition, etc.)
  Page<InstrumentDto> getFilteredInstruments(
          String type,
          String location,
          String condition,
          String brand,
          String assignedStudent,
          Pageable pageable
  );
}
