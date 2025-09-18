package com.example.demoWeb.account.service.impl;

import com.example.demoWeb.account.dto.request.RoleRequest;
import com.example.demoWeb.account.dto.response.RoleResponse;

import com.example.demoWeb.exception.DuplicateResourceException;
import com.example.demoWeb.account.mapper.RoleMapper;

import com.example.demoWeb.account.model.Role;

import com.example.demoWeb.account.repository.RolesRepository;

import com.example.demoWeb.account.service.RoleService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;
    private final RoleMapper roleMapper;

    //GetAllRole
    public List<RoleResponse> getAllRoles() {
        return roleMapper.toResponseList(rolesRepository.findAll());
    }

    //CREAT
    public RoleResponse create(@NotNull RoleRequest request) {

        rolesRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(role -> {
                    throw new DuplicateResourceException("Role name already exists " + request.getName());
                });

        Role role = roleMapper.createToEntity(request);
        Role saved = rolesRepository.save(role);
        return roleMapper.toResponse(saved);
    }

    //UPDATE
    public Optional<RoleResponse> update(Long id, RoleRequest request) {
        rolesRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(role -> {
                    throw new DuplicateResourceException("Role name already exists " + request.getName());
                });

        return rolesRepository.findById(id)
                .map(role -> {
                    roleMapper.updateToEntity(request, role);
                    Role saved = rolesRepository.save(role);
                    return roleMapper.toResponse(saved);
                });

    }


}
