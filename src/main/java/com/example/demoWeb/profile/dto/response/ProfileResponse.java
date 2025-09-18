package com.example.demoWeb.profile.dto.response;

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
