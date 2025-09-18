package com.example.demoWeb.profile.service;

import com.example.demoWeb.profile.dto.request.ProfileRequest;
import com.example.demoWeb.profile.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse create(ProfileRequest request, String username);

    ProfileResponse getById(Long id);


    ProfileResponse getByUsername(String username);

    ProfileResponse updateById(Long id, ProfileRequest request);

    ProfileResponse updateByUsername(String username, ProfileRequest request);

    void deleteProfile(Long id);
}
