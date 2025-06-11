package com.zacthompson.backend.service;

import com.zacthompson.backend.dto.InstrumentDto;
import com.zacthompson.backend.entity.Instrument;
import com.zacthompson.backend.mapper.InstrumentMapper;
import com.zacthompson.backend.repository.InstrumentRepository;
import com.zacthompson.backend.repository.InstrumentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
  Implementation of the InstrumentService interface.
  Handles business logic and database filtering using dynamic Specifications and pagination.
 */
@Service
public class InstrumentServiceImpl implements InstrumentService {

  private final InstrumentRepository instrumentRepository;

  @Autowired
  public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
    this.instrumentRepository = instrumentRepository;
  }

  @Override
  public List<InstrumentDto> getAllInstruments(org.springframework.data.domain.Sort sort) {
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
    updated.setId(existing.getId());
    return InstrumentMapper.toDto(instrumentRepository.save(updated));
  }

  @Override
  public void deleteInstrument(Long id) {
    if (!instrumentRepository.existsById(id)) {
      throw new RuntimeException("Instrument not found with id: " + id);
    }
    instrumentRepository.deleteById(id);
  }

  /**
   * Returns a paginated, filtered list of instruments based on optional query parameters.
   */
  @Override
  public Page<InstrumentDto> getFilteredInstruments(
          String type,
          String location,
          String condition,
          String brand,
          String assignedStudent,
          Pageable pageable
  ) {
    Specification<Instrument> spec = (root, query, cb) -> cb.conjunction();

    if (type != null) spec = spec.and(InstrumentSpecification.hasType(type));
    if (location != null) spec = spec.and(InstrumentSpecification.hasLocation(location));
    if (condition != null) spec = spec.and(InstrumentSpecification.hasCondition(condition));
    if (brand != null) spec = spec.and(InstrumentSpecification.hasBrand(brand));
    if (assignedStudent != null) spec = spec.and(InstrumentSpecification.hasAssignedStudent(assignedStudent));

    return instrumentRepository.findAll(spec, pageable).map(InstrumentMapper::toDto);
  }
}
