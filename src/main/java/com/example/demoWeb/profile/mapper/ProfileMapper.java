package com.example.demoWeb.profile.mapper;

import com.example.demoWeb.profile.dto.request.ProfileRequest;
import com.example.demoWeb.profile.dto.response.ProfileResponse;
import com.example.demoWeb.profile.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {


    ProfileResponse toResponse(Profile profile);

    void updateToEntity(ProfileRequest updateRequest, @MappingTarget Profile profile);


    Profile createToEntity(ProfileRequest createRequest);


}
