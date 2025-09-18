package com.example.demoWeb.post.service.impl;

import com.example.demoWeb.account.service.UserService;
import com.example.demoWeb.post.dto.request.PostRequest;
import com.example.demoWeb.post.dto.response.PostResponse;
import com.example.demoWeb.exception.ResourceNotFoundException;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.post.mapper.PostMapper;
import com.example.demoWeb.post.model.Post;
import com.example.demoWeb.account.model.User;
import com.example.demoWeb.post.repository.PostRepository;
import com.example.demoWeb.account.repository.UserRepository;
import com.example.demoWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
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

    @Override
    public PostResponse getById(Long id) {
        return postMapper.toResponse(postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id)));
    }

    @Override
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return postMapper.toResponseList(user.getPosts());
    }

    @Transactional
    @Override
    public PostResponse create(String username, PostRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Post post = postMapper.toEntity(request);
        post.setUser(user);

        Post saved = postRepository.save(post);
        return postMapper.toResponse(saved);
    }

    @Transactional
    @Override
    public PostResponse edit(String username, PostRequest request, Long id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist"));
        if (post.getUser().getUsername().equals(username)) {
            postMapper.updateEntity(request, post);
            Post saved = postRepository.save(post);
            return postMapper.toResponse(saved);
        } else throw new RuntimeException("You do not have permission to edit this post");


    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!postRepository.existsById(id))
            throw new ResourceNotFoundException("Post not found with id " + id);
        postRepository.deleteById(id);
    }
}
