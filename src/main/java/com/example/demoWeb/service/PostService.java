package com.example.demoWeb.service;

import com.example.demoWeb.dto.request.PostRequest;
import com.example.demoWeb.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getAll();

    PostResponse create(PostRequest request);

    void delete(Long id);
}
