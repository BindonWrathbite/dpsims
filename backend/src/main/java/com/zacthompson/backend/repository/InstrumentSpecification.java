package com.zacthompson.backend.repository;

import com.zacthompson.backend.entity.Condition;
import com.zacthompson.backend.entity.Instrument;
import org.springframework.data.jpa.domain.Specification;

public class InstrumentSpecification {

  public static Specification<Instrument> hasType(String type) {
    return (root, query, builder) -> builder.equal(
            builder.lower(root.get("type")), type.toLowerCase()
    );
  }

  public static Specification<Instrument> hasLocation(String location) {
    return (root, query, builder) -> builder.like(
            builder.lower(root.get("location")), "%" + location.toLowerCase() + "%"
    );
  }

  public static Specification<Instrument> hasCondition(String condition) {
    return (root, query, builder) -> {
      try {
        Condition cond = Condition.valueOf(condition.toUpperCase());
        return builder.equal(root.get("condition"), cond);
      } catch (IllegalArgumentException e) {
        return builder.conjunction(); // No filter applied if invalid
      }
    };
  }

  public static Specification<Instrument> hasBrand(String brand) {
    return (root, query, builder) -> builder.equal(
            builder.lower(root.get("brand")), brand.toLowerCase()
    );
  }

  public static Specification<Instrument> hasAssignedStudent(String student) {
    return (root, query, builder) -> builder.like(
            builder.lower(root.get("assignedStudent")), "%" + student.toLowerCase() + "%"
    );
  }
}
