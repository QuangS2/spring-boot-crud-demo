package com.example.demoWeb.service;

import com.example.demoWeb.dto.request.RoleRequest;
import com.example.demoWeb.dto.response.RoleResponse;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleResponse> getAllRoles();

    RoleResponse create(RoleRequest request);

    Optional<RoleResponse> update(Long id, RoleRequest request);
}
