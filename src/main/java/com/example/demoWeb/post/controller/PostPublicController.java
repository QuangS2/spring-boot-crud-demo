package com.example.demoWeb.post.controller;

import com.example.demoWeb.post.dto.response.PostResponse;
import com.example.demoWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/posts")
public class PostPublicController {
    private final PostService postService;

    //get getall posts
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    //get /posts/id get 1 posts detail
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

}
