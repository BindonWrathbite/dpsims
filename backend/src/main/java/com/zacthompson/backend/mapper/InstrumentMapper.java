package com.zacthompson.backend.mapper;

import com.zacthompson.backend.dto.*;
import com.zacthompson.backend.entity.*;

import java.util.List;
import java.util.stream.Collectors;

/*
  A manual mapper to convert between Instrument entity and InstrumentDto.
  Keeps entity logic separate from data transfer concerns.
 */
public class InstrumentMapper {

  /*
    Converts an Instrument entity into a DTO for sending data to the client.
   */
  public static InstrumentDto toDto(Instrument instrument) {
    if (instrument == null) return null;

    InstrumentDto dto = new InstrumentDto();

    // Copy basic fields
    dto.setId(instrument.getId());
    dto.setType(instrument.getType());
    dto.setBrand(instrument.getBrand());
    dto.setSerialNumber(instrument.getSerialNumber());
    dto.setInventoryNumber(instrument.getInventoryNumber());
    dto.setCondition(instrument.getCondition());
    dto.setPurchaseDate(instrument.getPurchaseDate());
    dto.setPurchasePrice(instrument.getPurchasePrice());
    dto.setLocation(instrument.getLocation());
    dto.setAssignedStudent(instrument.getAssignedStudent());

    // Map list of RepairNote to RepairNoteDto
    dto.setRepairs(instrument.getRepairs() != null
                    ? instrument.getRepairs().stream().map(r -> {
              RepairNoteDto rd = new RepairNoteDto();
              rd.setDate(r.getDate());
              rd.setNote(r.getNote());
              return rd;
            }).collect(Collectors.toList())
                    : null
    );

    // Map list of GeneralNote to GeneralNoteDto
    dto.setNotes(instrument.getNotes() != null
                    ? instrument.getNotes().stream().map(n -> {
              GeneralNoteDto gd = new GeneralNoteDto();
              gd.setDate(n.getDate());
              gd.setNote(n.getNote());
              return gd;
            }).collect(Collectors.toList())
                    : null
    );

    return dto;
  }

  /*
    Converts an InstrumentDto into an entity for saving to the database.
   */
  public static Instrument toEntity(InstrumentDto dto) {
    if (dto == null) return null;

    Instrument instrument = new Instrument();

    // Copy basic fields
    instrument.setId(dto.getId());
    instrument.setType(dto.getType());
    instrument.setBrand(dto.getBrand());
    instrument.setSerialNumber(dto.getSerialNumber());
    instrument.setInventoryNumber(dto.getInventoryNumber());
    instrument.setCondition(dto.getCondition());
    instrument.setPurchaseDate(dto.getPurchaseDate());
    instrument.setPurchasePrice(dto.getPurchasePrice());
    instrument.setLocation(dto.getLocation());
    instrument.setAssignedStudent(dto.getAssignedStudent());

    // Map list of RepairNoteDto to RepairNote
    instrument.setRepairs(dto.getRepairs() != null
                    ? dto.getRepairs().stream().map(r -> {
              RepairNote rn = new RepairNote();
              rn.setDate(r.getDate());
              rn.setNote(r.getNote());
              return rn;
            }).collect(Collectors.toList())
                    : null
    );

    // Map list of GeneralNoteDto to GeneralNote
    instrument.setNotes(dto.getNotes() != null
                    ? dto.getNotes().stream().map(n -> {
              GeneralNote gn = new GeneralNote();
              gn.setDate(n.getDate());
              gn.setNote(n.getNote());
              return gn;
            }).collect(Collectors.toList())
                    : null
    );

    return instrument;
  }
}
