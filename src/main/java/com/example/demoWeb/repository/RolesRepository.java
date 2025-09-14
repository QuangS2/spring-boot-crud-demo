package com.example.demoWeb.repository;

import com.example.demoWeb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
