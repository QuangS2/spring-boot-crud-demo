package com.example.demoWeb.account.service;

import com.example.demoWeb.account.dto.request.LoginRequest;
import com.example.demoWeb.account.dto.request.UserRegisterRequest;
import com.example.demoWeb.account.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse register(UserRegisterRequest request);

    AuthResponse refreshToken(String refreshToken);

    void logout(String token);
}
