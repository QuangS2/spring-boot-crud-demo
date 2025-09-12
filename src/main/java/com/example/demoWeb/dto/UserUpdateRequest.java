package com.example.demoWeb.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be >= 0")
    @Max(value = 120, message = "Age must be <= 120")
    private int age;

    private String password;
}
