package com.example.demoWeb.repository;

import com.example.demoWeb.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
