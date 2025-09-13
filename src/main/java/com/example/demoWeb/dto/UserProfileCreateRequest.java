package com.example.demoWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileCreateRequest {

    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    private String bio;

    @NotNull(message = "User Id cannot be null")
    private Long userId;
}
