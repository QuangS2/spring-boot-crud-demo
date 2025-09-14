package com.example.demoWeb.service;

import com.example.demoWeb.dto.UserResponse;
import com.example.demoWeb.dto.UserCreateRequest;
import com.example.demoWeb.dto.UserUpdateRequest;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.mapper.UserMapper;
import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse);
    }

    public UserResponse createUser(UserCreateRequest request) {

        User user = userMapper.createToEntity(request);
        
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
}
