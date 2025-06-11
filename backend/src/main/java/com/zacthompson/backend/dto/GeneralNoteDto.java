package com.zacthompson.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/*
  DTO for a general note attached to an instrument.
  Mirrors the embedded GeneralNote entity but keeps persistence logic out.
 */
@Getter
@Setter
public class GeneralNoteDto {
  private LocalDate localDate; // The date associated with the note
  private String note; // The content of the general note
}
