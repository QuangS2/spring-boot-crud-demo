package com.example.demoWeb.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileResponse {
    private final Long id;
    private final String address;
    private final String phone;
    private final String bio;
    private final String userName;
}
