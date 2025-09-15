package com.example.demoWeb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final Long id;
    private final String name;
    private final int age;
    private final ProfileResponse profile;
    private final Set<String> roles;
}
