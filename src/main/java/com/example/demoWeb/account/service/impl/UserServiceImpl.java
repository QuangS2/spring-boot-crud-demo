package com.example.demoWeb.account.service.impl;

import com.example.demoWeb.account.dto.request.UserRegisterRequest;
import com.example.demoWeb.account.dto.response.UserResponse;
import com.example.demoWeb.account.dto.request.UserUpdateRequest;
import com.example.demoWeb.exception.ResourceNotFoundException;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.account.mapper.UserMapper;
import com.example.demoWeb.account.model.Role;
import com.example.demoWeb.account.model.User;
import com.example.demoWeb.account.repository.RolesRepository;
import com.example.demoWeb.account.repository.UserRepository;
import com.example.demoWeb.account.service.UserService;
import com.example.demoWeb.profile.model.Profile;
import com.example.demoWeb.profile.repository.ProfileRepository;
import com.example.demoWeb.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    public Page<UserResponse> getUsers(String name, Pageable pageable) {
        Page<User> userPage = userRepository.findByNameContainingIgnoreCase(name, pageable);
        return userPage.map(userMapper::toResponse);
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse);
    }

    @Override
    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toResponse);
    }

    @Transactional
    @Override
    public UserResponse create(UserRegisterRequest request) {
        Role role = rolesRepository.findByNameIgnoreCase("User")
                .orElseThrow(() -> new ResourceNotFoundException("Role user is not exist"));
        User user = userMapper.createToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Profile profile = new Profile();
        user.getRoles().add(role);
        user.setProfile(profile);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse getCurrentUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toResponse)
                .orElseThrow(() ->
                        new UserNotFoundException(username));
    }

//    @Transactional
//    public UserResponse register(RegisterRequest request) {
//        Role role = rolesRepository.findByNameIgnoreCase("User")
//                .orElseGet(() -> {
//                    Role newRole = Role.builder()
//                            .name("User")
//                            .build();
//                    return rolesRepository.save(newRole);
//                });
//        User user = userMapper.createToEntity(request);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getRoles().add(role);
//        User saved = userRepository.save(user);
//
//        return userMapper.toResponse(saved);
//    }


    //api/user/update - //api/admin/id/user/id/update?
    @Transactional
    @Override
    public Optional<UserResponse> updateUser(Long id, UserUpdateRequest request) {
        return userRepository.findById(id)
                .map(u -> {
                    userMapper.updateToEntity(request, u);
                    User saved = userRepository.save(u);
                    return userMapper.toResponse(saved);
                });
    }

    @Transactional
    @Override
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
