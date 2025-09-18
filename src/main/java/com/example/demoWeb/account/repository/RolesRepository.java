package com.example.demoWeb.account.repository;

import com.example.demoWeb.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNameIgnoreCase(String name);
}
