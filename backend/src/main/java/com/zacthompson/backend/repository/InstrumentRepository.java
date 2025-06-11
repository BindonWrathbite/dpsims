package com.zacthompson.backend.repository;

import com.zacthompson.backend.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
  JPA repository for Instrument, extended with support for Specifications.
 */
@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long>, JpaSpecificationExecutor<Instrument> {
  // Custom methods no longer needed — all handled dynamically via Specifications
}
