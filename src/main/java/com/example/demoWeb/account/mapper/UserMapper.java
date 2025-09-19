package com.example.demoWeb.account.mapper;

import com.example.demoWeb.account.dto.request.UserRegisterRequest;
import com.example.demoWeb.account.dto.response.UserResponse;
import com.example.demoWeb.account.dto.request.UserUpdateRequest;
import com.example.demoWeb.account.model.Role;
import com.example.demoWeb.account.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "roles", expression = "java(mapRole(user.getRoles()))")
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> userList);


    void updateToEntity(UserUpdateRequest updateRequest, @MappingTarget User user);


    User createToEntity(UserRegisterRequest createRequest);

    //custom mapper
    //Set<Role> -> Set<String> // Entity -> Response
    default Set<String> mapRole(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
