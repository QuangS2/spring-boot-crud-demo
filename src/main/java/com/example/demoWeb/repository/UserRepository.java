package com.example.demoWeb.repository;

import com.example.demoWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
