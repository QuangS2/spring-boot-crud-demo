package com.example.demoWeb.mapper;

import com.example.demoWeb.dto.request.ProfileCreateRequest;
import com.example.demoWeb.dto.response.ProfileResponse;
import com.example.demoWeb.dto.request.ProfileUpdateRequest;
import com.example.demoWeb.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {


    ProfileResponse toResponse(Profile profile);

    void updateToEntity(ProfileUpdateRequest updateRequest, @MappingTarget Profile profile);


    Profile createToEntity(ProfileCreateRequest createRequest);


}
