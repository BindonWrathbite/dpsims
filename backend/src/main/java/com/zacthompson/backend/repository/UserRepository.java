package com.zacthompson.backend.repository;

import com.zacthompson.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> { // Extends JPARepository to provide CRUD operations (find by id, save, deleteId, findAll()
    Optional<User> findByEmail(String email);   // used to find a user by email, which is unique
}
