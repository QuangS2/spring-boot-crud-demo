package com.example.demoWeb.post.repository;

import com.example.demoWeb.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
