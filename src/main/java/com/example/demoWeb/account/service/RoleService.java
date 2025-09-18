package com.example.demoWeb.account.service;

import com.example.demoWeb.account.dto.request.RoleRequest;
import com.example.demoWeb.account.dto.response.RoleResponse;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleResponse> getAllRoles();

    RoleResponse create(RoleRequest request);

    Optional<RoleResponse> update(Long id, RoleRequest request);
}
