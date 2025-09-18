package com.example.demoWeb.profile.model;

import com.example.demoWeb.account.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String phone;

    private String bio;

    @OneToOne(mappedBy = "profile")
    private User user;
}
