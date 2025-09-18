package com.example.demoWeb.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    private String bio;
}
