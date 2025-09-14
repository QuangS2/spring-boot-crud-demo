package com.example.demoWeb.mapper;

import com.example.demoWeb.dto.PostRequest;
import com.example.demoWeb.dto.PostResponse;
import com.example.demoWeb.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "username", expression = "java(post.getUser().getName())")
    PostResponse toResponse(Post post);

    List<PostResponse> toResponseList(List<Post> posts);

    Post toEntity(PostRequest request);
}
