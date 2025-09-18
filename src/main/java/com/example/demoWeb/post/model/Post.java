package com.example.demoWeb.post.model;

import com.example.demoWeb.account.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    // Many post belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
