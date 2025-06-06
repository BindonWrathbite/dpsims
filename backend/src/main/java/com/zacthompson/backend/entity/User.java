package com.zacthompson.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity  // Required to mark this class as a JPA entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user", schema = "public") // Avoid "user" (a reserved word in many SQL dialects)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role; // e.g., "ADMIN", "USER"
}
