package com.zacthompson.backend.dto;

import com.zacthompson.backend.entity.Condition;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
  Data Transfer Object for the Instrument entity.
  Used for sending/receiving instrument data via the API without exposing internal logic or JPA configuration.
 */
@Getter
@Setter
public class InstrumentDto {
  private Long id; // Auto-generated primary key

  private String type; // e.g., Trombone, Trumpet
  private String brand;
  private String serialNumber;
  private String inventoryNumber;

  private List<RepairNoteDto> repairs; // List of repair notes (with dates)
  private Condition condition;         // Enum for condition: EXCELLENT, GOOD, etc.

  private LocalDate purchaseDate;
  private BigDecimal purchasePrice;    // Two decimal precision for currency

  private List<GeneralNoteDto> notes;  // List of general notes (with dates)

  private String location;             // Location name for now
  private String assignedStudent;      // Assigned student name for now
}
