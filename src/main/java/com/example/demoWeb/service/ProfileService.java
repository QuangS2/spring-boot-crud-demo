package com.example.demoWeb.service;

import com.example.demoWeb.dto.request.ProfileCreateRequest;
import com.example.demoWeb.dto.request.ProfileUpdateRequest;
import com.example.demoWeb.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse create(ProfileCreateRequest request);

    ProfileResponse getById(Long id);

    ProfileResponse getByUserId(Long id);

    ProfileResponse updateById(Long id, ProfileUpdateRequest request);

    ProfileResponse updateByUserId(Long id, ProfileUpdateRequest request);

    void deleteProfile(Long id);
}
