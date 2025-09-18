package com.example.demoWeb.account.repository;

import com.example.demoWeb.account.model.Token;
import com.example.demoWeb.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByUsername(String username);

}
