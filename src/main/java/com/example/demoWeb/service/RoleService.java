package com.example.demoWeb.service;

import com.example.demoWeb.dto.RoleRequest;
import com.example.demoWeb.dto.RoleResponse;
import com.example.demoWeb.dto.UserResponse;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.mapper.RoleMapper;
import com.example.demoWeb.mapper.UserMapper;
import com.example.demoWeb.model.Role;
import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.RolesRepository;
import com.example.demoWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RolesRepository rolesRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //GetAllRole
    public List<RoleResponse> getAllRoles() {
        return roleMapper.toResponseList(rolesRepository.findAll());
    }

    //CREAT
    public RoleResponse create(RoleRequest request) {

        request.setName(request.getName().toLowerCase());
        if (rolesRepository.existsByName(request.getName()))
            throw new RuntimeException("Role name already exists " + request.getName());

        Role role = roleMapper.createToEntity(request);

        Role saved = rolesRepository.save(role);
        return roleMapper.toResponse(saved);
    }

    //UPDATE
    public Optional<RoleResponse> update(Long id, RoleRequest request) {
        return rolesRepository.findById(id)
                .map(role -> {
                    request.setName(request.getName().toLowerCase());
                    if (rolesRepository.existsByName(request.getName()))
                        throw new RuntimeException("Role name already exists " + request.getName());
                    roleMapper.updateToEntity(request, role);
                    Role saved = rolesRepository.save(role);
                    return roleMapper.toResponse(saved);
                });

    }


}
