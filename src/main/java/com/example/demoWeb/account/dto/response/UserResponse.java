package com.example.demoWeb.account.dto.response;

import com.example.demoWeb.profile.dto.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final Long id;
    private String username;
    private final String name;
    private final int age;
    private final ProfileResponse profile;
    private final Set<String> roles;
}
