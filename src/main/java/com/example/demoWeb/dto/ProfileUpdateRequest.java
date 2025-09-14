package com.example.demoWeb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequest {
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    private String bio;
}
