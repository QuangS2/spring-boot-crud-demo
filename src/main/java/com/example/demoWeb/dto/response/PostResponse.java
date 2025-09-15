package com.example.demoWeb.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String username;
}
