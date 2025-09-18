package com.example.demoWeb.profile.repository;

import com.example.demoWeb.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
