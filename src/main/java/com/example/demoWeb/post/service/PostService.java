package com.example.demoWeb.post.service;

import com.example.demoWeb.post.dto.request.PostRequest;
import com.example.demoWeb.post.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getAll();

    PostResponse getById(Long id);

    List<PostResponse> getPostsByUsername(String username);

    PostResponse create(String username, PostRequest request);

    PostResponse edit(String username, PostRequest request, Long id);

    void delete(Long id);
}
