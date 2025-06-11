package com.zacthompson.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/*
  DTO for a repair note attached to an instrument.
  Mirrors the embedded RepairNote entity but keeps persistence logic out.
 */
@Getter
@Setter
public class RepairNoteDto {
  private LocalDate date; // When the repair occurred
  private String note;    // What was the repair
}
