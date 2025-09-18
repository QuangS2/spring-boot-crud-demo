package com.example.demoWeb.post.mapper;

import com.example.demoWeb.post.dto.request.PostRequest;
import com.example.demoWeb.post.dto.response.PostResponse;
import com.example.demoWeb.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "username", expression = "java(post.getUser().getName())")
    PostResponse toResponse(Post post);

    List<PostResponse> toResponseList(List<Post> posts);

    Post toEntity(PostRequest request);

    void updateEntity(PostRequest request, @MappingTarget Post post);
}
