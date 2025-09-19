package com.example.demoWeb.account.repository;

import com.example.demoWeb.account.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
