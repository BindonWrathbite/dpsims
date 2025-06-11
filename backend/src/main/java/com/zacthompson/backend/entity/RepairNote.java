package com.zacthompson.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class RepairNote {
  @Column(name = "date")
  private LocalDate date;

  @Column(name = "note")
  private String note;
}
