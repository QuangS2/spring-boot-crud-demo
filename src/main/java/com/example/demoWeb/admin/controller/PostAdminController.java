package com.example.demoWeb.admin.controller;

import com.example.demoWeb.post.dto.response.PostResponse;
import com.example.demoWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/posts")
public class PostAdminController {

    private final PostService postService;

    //get list posts
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    //delete posts/id - delete post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
