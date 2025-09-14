package com.example.demoWeb.mapper;

import com.example.demoWeb.dto.ProfileCreateRequest;
import com.example.demoWeb.dto.ProfileResponse;
import com.example.demoWeb.dto.ProfileUpdateRequest;
import com.example.demoWeb.model.Profile;
import com.example.demoWeb.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {


    ProfileResponse toResponse(Profile profile);

    void updateToEntity(ProfileUpdateRequest updateRequest, @MappingTarget Profile profile);


    Profile createToEntity(ProfileCreateRequest createRequest);


}
