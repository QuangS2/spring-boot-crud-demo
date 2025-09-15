package com.example.demoWeb.mapper;

import com.example.demoWeb.dto.request.UserCreateRequest;
import com.example.demoWeb.dto.response.UserResponse;
import com.example.demoWeb.dto.request.UserUpdateRequest;
import com.example.demoWeb.model.Role;
import com.example.demoWeb.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRole(user.getRoles()))")
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> userList);

    void updateToEntity(UserUpdateRequest updateRequest, @MappingTarget User user);

    User createToEntity(UserCreateRequest createRequest);

    //custom mapper
    //Set<Role> -> Set<String> // Entity -> Response
    default Set<String> mapRole(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
