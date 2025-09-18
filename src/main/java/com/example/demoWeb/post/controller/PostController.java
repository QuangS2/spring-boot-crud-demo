package com.example.demoWeb.post.controller;

import com.example.demoWeb.account.model.User;
import com.example.demoWeb.account.service.UserService;
import com.example.demoWeb.post.dto.request.PostRequest;
import com.example.demoWeb.post.dto.response.PostResponse;

import com.example.demoWeb.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    // get /my get all my post
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll(@AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(postService.getPostsByUsername(userDetails.getUsername()));
    }


    //post  create new post

    @PostMapping
    public ResponseEntity<PostResponse> create(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody PostRequest request) {
        PostResponse response = postService.create(userDetails.getUsername(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //put posts/id edit post
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> editPost(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody PostRequest request, @PathVariable Long id) {

        return ResponseEntity.ok(postService.edit(userDetails.getUsername(),
                request, id));

    }

    //delete posts/id delete post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        if (postService.getById(id).getUsername().equals(userDetails.getUsername())) {
            postService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
