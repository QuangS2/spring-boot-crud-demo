package com.example.demoWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    @NotBlank(message = "Tittle cannot be blank")
    private String title;

    @Size(min = 10, message = "Content must be at least 10 characters")
    private String content;

    @NotNull(message = "user id cannot be Null")
    private Long userId;
}
