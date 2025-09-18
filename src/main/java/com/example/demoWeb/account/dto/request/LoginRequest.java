package com.example.demoWeb.account.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequest {

    @NotBlank(message = "username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
