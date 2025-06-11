package com.zacthompson.backend.service;

import com.zacthompson.backend.dto.InstrumentDto;
import com.zacthompson.backend.entity.Condition;
import com.zacthompson.backend.entity.Instrument;
import com.zacthompson.backend.mapper.InstrumentMapper;
import com.zacthompson.backend.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
  Implementation of the InstrumentService interface.
  Handles business logic for instrument operations and interacts with the repository and mapper layers.
 */
@Service
public class InstrumentServiceImpl implements InstrumentService {

  private final InstrumentRepository instrumentRepository;

  @Autowired
  public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
    this.instrumentRepository = instrumentRepository;
  }

  @Override
  public List<InstrumentDto> getAllInstruments(Sort sort) {
    return instrumentRepository.findAll(sort).stream()
            .map(InstrumentMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public InstrumentDto getInstrumentById(Long id) {
    Instrument instrument = instrumentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Instrument not found with id: " + id));
    return InstrumentMapper.toDto(instrument);
  }

  @Override
  public InstrumentDto createInstrument(InstrumentDto dto) {
    Instrument saved = instrumentRepository.save(InstrumentMapper.toEntity(dto));
    return InstrumentMapper.toDto(saved);
  }

  @Override
  public InstrumentDto updateInstrument(Long id, InstrumentDto dto) {
    Instrument existing = instrumentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Instrument not found with id: " + id));

    Instrument updated = InstrumentMapper.toEntity(dto);
    updated.setId(existing.getId()); // Keep existing ID
    return InstrumentMapper.toDto(instrumentRepository.save(updated));
  }

  @Override
  public void deleteInstrument(Long id) {
    if (!instrumentRepository.existsById(id)) {
      throw new RuntimeException("Instrument not found with id: " + id);
    }
    instrumentRepository.deleteById(id);
  }

  @Override
  public List<InstrumentDto> getInstrumentsByType(String type, Sort sort) {
    return instrumentRepository.findByTypeIgnoreCase(type, sort).stream()
            .map(InstrumentMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<InstrumentDto> getInstrumentsByTypeAndLocation(String type, String location, Sort sort) {
    return instrumentRepository.findByTypeAndLocationContainingIgnoreCase(type, location, sort).stream()
            .map(InstrumentMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<InstrumentDto> getInstrumentsByTypeAndCondition(String type, String conditionStr, Sort sort) {
    Condition condition = Condition.valueOf(conditionStr.toUpperCase()); // Parse string to enum safely
    return instrumentRepository.findByTypeAndConditionIgnoreCase(type, condition, sort).stream()
            .map(InstrumentMapper::toDto)
            .collect(Collectors.toList());
  }
}
