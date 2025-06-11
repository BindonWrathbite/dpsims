package com.zacthompson.backend.repository;

import com.zacthompson.backend.entity.Condition;
import com.zacthompson.backend.entity.Instrument;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

  // Basic filters with sorting support
  List<Instrument> findByTypeIgnoreCase(String type, Sort sort);
  List<Instrument> findByBrandIgnoreCase(String brand, Sort sort);
  List<Instrument> findByCondition(Condition condition, Sort sort);
  List<Instrument> findByLocationContainingIgnoreCase(String location, Sort sort);
  List<Instrument> findByAssignedStudentContainingIgnoreCase(String assignedStudent, Sort sort);

  // Combined filters
  List<Instrument> findByTypeAndConditionIgnoreCase(String type, Condition condition, Sort sort);
  List<Instrument> findByTypeAndLocationContainingIgnoreCase(String type, String location, Sort sort);
  List<Instrument> findByTypeAndAssignedStudentContainingIgnoreCase(String type, String assignedStudent, Sort sort);
  List<Instrument> findByLocationContainingIgnoreCaseAndCondition(String location, Condition condition);

  // Range filters (not typically sorted this way, but can be)
  List<Instrument> findByPurchaseDateBetween(LocalDate startDate, LocalDate endDate, Sort sort);
  List<Instrument> findByPurchasePriceBetween(BigDecimal purchasePrice, BigDecimal purchasePrice2, Sort sort);

}
