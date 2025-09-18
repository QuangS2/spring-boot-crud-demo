package com.example.demoWeb.account.service;

import com.example.demoWeb.account.dto.request.UserRegisterRequest;
import com.example.demoWeb.account.dto.request.UserUpdateRequest;
import com.example.demoWeb.account.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(Long id);

    Optional<UserResponse> getUserByUsername(String username);

    UserResponse create(UserRegisterRequest request);

    UserResponse getCurrentUserByUsername(String username);
//    UserResponse register(RegisterRequest request);

    Optional<UserResponse> updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);

    UserResponse assignRole(Long userId, String roleName);

    UserResponse removeRoleFromUser(Long userId, String roleName);
}
