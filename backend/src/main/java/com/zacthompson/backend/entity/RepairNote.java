package com.zacthompson.backend.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class RepairNote {
  private LocalDate date;
  private String note;
}
