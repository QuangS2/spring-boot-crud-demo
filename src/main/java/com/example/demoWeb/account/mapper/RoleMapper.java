package com.example.demoWeb.account.mapper;

import com.example.demoWeb.account.dto.request.RoleRequest;
import com.example.demoWeb.account.dto.response.RoleResponse;
import com.example.demoWeb.account.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toResponse(Role role);

    List<RoleResponse> toResponseList(List<Role> roleList);

    Role createToEntity(RoleRequest request);

    void updateToEntity(RoleRequest request, @MappingTarget Role role);
}
