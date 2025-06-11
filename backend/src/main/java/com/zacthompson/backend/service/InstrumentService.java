package com.zacthompson.backend.service;

import com.zacthompson.backend.dto.InstrumentDto;
import org.springframework.data.domain.Sort;

import java.util.List;

/*
  Interface defining the service layer for instrument-related operations.
  Now includes a flexible filter method that replaces multiple endpoint-specific ones.
 */
public interface InstrumentService {

  List<InstrumentDto> getAllInstruments(Sort sort);

  InstrumentDto getInstrumentById(Long id);

  InstrumentDto createInstrument(InstrumentDto dto);

  InstrumentDto updateInstrument(Long id, InstrumentDto dto);

  void deleteInstrument(Long id);

  /*
    Flexible filter method supporting optional filtering by type, location, condition, brand, and assigned student.
   */
  List<InstrumentDto> getFilteredInstruments(
          String type,
          String location,
          String condition,
          String brand,
          String assignedStudent,
          Sort sort
  );
}
