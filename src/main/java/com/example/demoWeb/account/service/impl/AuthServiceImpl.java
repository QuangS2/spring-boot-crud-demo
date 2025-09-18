package com.example.demoWeb.account.service.impl;

import com.example.demoWeb.account.dto.request.LoginRequest;
import com.example.demoWeb.account.dto.request.UserRegisterRequest;
import com.example.demoWeb.account.dto.response.AuthResponse;
import com.example.demoWeb.account.dto.response.UserResponse;
import com.example.demoWeb.account.mapper.UserMapper;
import com.example.demoWeb.account.model.Role;
import com.example.demoWeb.account.model.User;
import com.example.demoWeb.account.repository.RolesRepository;
import com.example.demoWeb.account.repository.TokenRepository;
import com.example.demoWeb.account.repository.UserRepository;
import com.example.demoWeb.account.service.AuthService;
import com.example.demoWeb.account.service.UserService;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RolesRepository rolesRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserService userService;

    @Override

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        return new AuthResponse(jwtService.generateToken(request.getUsername()),
                jwtService.generateRefreshToken(request.getUsername()));
    }

    @Override
    @Transactional
    public AuthResponse register(UserRegisterRequest request) {

        User user = userRepository.findByUsername(
                userService.create(request).getUsername()
        ).orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        return new AuthResponse(jwtService.generateToken(request.getUsername()),
                jwtService.generateRefreshToken(request.getUsername()));
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken); // get back username
        if (username == null || !jwtService.isTokenValid(refreshToken, username)) {
            throw new RuntimeException("Invalid refresh token");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        String newAccessToken = jwtService.generateToken(username);
        return new AuthResponse(newAccessToken, refreshToken);
    }

    @Override
    public void logout(String token) {
        String username = jwtService.extractUsername(token);

        tokenRepository.deleteByUsername(username);
    }
}
