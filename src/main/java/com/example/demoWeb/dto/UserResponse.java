package com.example.demoWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final Long id;
    private final String name;
    private final int age;
}
