package com.zacthompson.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "instrument", schema = "public")
@Getter
@Setter
public class Instrument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "brand")
  private String brand;

  @Column(name = "serial_number")
  private String serialNumber;

  @Column(name = "inventory_number", nullable = false)
  private String inventoryNumber;

  @ElementCollection
  @CollectionTable(name = "instrument_repairs", joinColumns = @JoinColumn(name = "instrument_id"))
  private List<RepairNote> repairs;

  @Enumerated(EnumType.STRING)
  @Column(name = "condition", nullable = false)
  private Condition condition;

  @Column(name = "purchase_date")
  private LocalDate purchaseDate;

  @Column(name = "purchase_price")
  private BigDecimal purchasePrice;

  @ElementCollection
  @CollectionTable(name = "instrument_notes", joinColumns = @JoinColumn(name = "instrument_id"))
  private List<GeneralNote> notes;

  @Column(name = "location")
  private String location;

  @Column(name = "assigned_student")
  private String assignedStudent;
}
