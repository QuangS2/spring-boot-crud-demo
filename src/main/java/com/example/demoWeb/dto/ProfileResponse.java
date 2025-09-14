package com.example.demoWeb.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse {
    private final Long id;
    private final String address;
    private final String phone;
    private final String bio;
}
