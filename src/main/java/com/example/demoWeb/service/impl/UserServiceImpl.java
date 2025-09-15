package com.example.demoWeb.service.impl;

import com.example.demoWeb.dto.response.UserResponse;
import com.example.demoWeb.dto.request.UserCreateRequest;
import com.example.demoWeb.dto.request.UserUpdateRequest;
import com.example.demoWeb.exception.ResourceNotFoundException;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.mapper.UserMapper;
import com.example.demoWeb.model.Role;
import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.RolesRepository;
import com.example.demoWeb.repository.UserRepository;
import com.example.demoWeb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RolesRepository rolesRepository;

    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse);
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        Role role = rolesRepository.findByNameIgnoreCase("User")
                .orElseGet(() -> {
                    Role newRole = Role.builder()
                            .name("User")
                            .build();
                    return rolesRepository.save(newRole);
                });
        User user = userMapper.createToEntity(request);
        user.getRoles().add(role);
        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    public Optional<UserResponse> updateUser(Long id, UserUpdateRequest request) {
        return userRepository.findById(id)
                .map(u -> {
                    userMapper.updateToEntity(request, u);
                    User saved = userRepository.save(u);
                    return userMapper.toResponse(saved);
                });
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id))
            throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }

    //USER - ASSIGN - ROLE
    public UserResponse assignRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Role role = rolesRepository.findByNameIgnoreCase(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.getRoles().add(role);

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    //USER REMOVE ROLE
    @Transactional
    public UserResponse removeRoleFromUser(Long userId, String roleName) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Role role = rolesRepository.findByNameIgnoreCase(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.getRoles().remove(role);
        return userMapper.toResponse(userRepository.save(user));
    }
}
