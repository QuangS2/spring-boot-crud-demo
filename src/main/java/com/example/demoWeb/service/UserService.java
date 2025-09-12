package com.example.demoWeb.service;

import com.example.demoWeb.dto.UserResponse;
import com.example.demoWeb.dto.UserCreateRequest;
import com.example.demoWeb.dto.UserUpdateRequest;
import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getAge()))
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()));
    }

    public UserResponse createUser(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());

        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(), saved.getName(), saved.getAge());
    }

    public Optional<UserResponse> updateUser(Long id, UserUpdateRequest request) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setName(request.getName());
                    u.setAge(request.getAge());
                    User saved = userRepository.save(u);
                    return new UserResponse(saved.getId(), saved.getName(), saved.getAge());
                });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
