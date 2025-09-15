package com.example.demoWeb.service.impl;

import com.example.demoWeb.dto.request.PostRequest;
import com.example.demoWeb.dto.response.PostResponse;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.mapper.PostMapper;
import com.example.demoWeb.model.Post;
import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.PostRepository;
import com.example.demoWeb.repository.UserRepository;
import com.example.demoWeb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public List<PostResponse> getAll() {
        return postMapper.toResponseList(postRepository.findAll());
    }

    public PostResponse create(PostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        Post post = postMapper.toEntity(request);
        post.setUser(user);

        Post saved = postRepository.save(post);
        return postMapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!postRepository.existsById(id))
            throw new RuntimeException("Post not found with id " + id);
        postRepository.deleteById(id);
    }
}
