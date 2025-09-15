package com.example.demoWeb.service.impl;

import com.example.demoWeb.dto.request.ProfileCreateRequest;
import com.example.demoWeb.dto.response.ProfileResponse;
import com.example.demoWeb.dto.request.ProfileUpdateRequest;

import com.example.demoWeb.exception.DuplicateResourceException;
import com.example.demoWeb.exception.ResourceNotFoundException;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.mapper.ProfileMapper;
import com.example.demoWeb.model.User;
import com.example.demoWeb.model.Profile;
import com.example.demoWeb.repository.ProfileRepository;
import com.example.demoWeb.repository.UserRepository;

import com.example.demoWeb.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    //    CREAT
    public ProfileResponse create(ProfileCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        if (user.getProfile() != null) throw new DuplicateResourceException("This User has profile already");

        Profile profile = profileMapper.createToEntity(request);
        profile.setUser(user);

        Profile saved = profileRepository.save(profile);

        return profileMapper.toResponse(saved);
    }

    //    GET BY ID
    public ProfileResponse getById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        return profileMapper.toResponse(profile);
    }

    //    GET BY USER ID
    public ProfileResponse getByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return getById(user.getProfile().getId());
    }

    //UPDATE BY ID
    public ProfileResponse updateById(Long id, ProfileUpdateRequest request) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        profileMapper.updateToEntity(request, profile);
        Profile saved = profileRepository.save(profile);
        return profileMapper.toResponse(saved);
    }

    //UPDATE BY USER ID
    public ProfileResponse updateByUserId(Long id, ProfileUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return updateById(user.getProfile().getId(), request);
    }

    //DELETE
    public void deleteProfile(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));

        User user = profile.getUser();
        if (user != null) {
            user.setProfile(null); // gỡ ràng buộc
            userRepository.save(user);
        }
        profileRepository.deleteById(id);
    }
}

