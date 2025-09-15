package com.example.demoWeb.service;

import com.example.demoWeb.dto.request.UserCreateRequest;
import com.example.demoWeb.dto.request.UserUpdateRequest;
import com.example.demoWeb.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(Long id);

    UserResponse createUser(UserCreateRequest request);

    Optional<UserResponse> updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);

    UserResponse assignRole(Long userId, String roleName);

    UserResponse removeRoleFromUser(Long userId, String roleName);
}
