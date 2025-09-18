package com.example.demoWeb.profile.service.impl;

import com.example.demoWeb.profile.dto.request.ProfileRequest;
import com.example.demoWeb.profile.dto.response.ProfileResponse;

import com.example.demoWeb.exception.DuplicateResourceException;
import com.example.demoWeb.exception.ResourceNotFoundException;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.profile.mapper.ProfileMapper;
import com.example.demoWeb.account.model.User;
import com.example.demoWeb.profile.model.Profile;
import com.example.demoWeb.profile.repository.ProfileRepository;
import com.example.demoWeb.account.repository.UserRepository;

import com.example.demoWeb.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    //    CREAT
    @Transactional
    @Override
    public ProfileResponse create(ProfileRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (user.getProfile() != null) throw new DuplicateResourceException("This User has profile already");

        Profile profile = profileMapper.createToEntity(request);
        profile.setUser(user);

        Profile saved = profileRepository.save(profile);

        return profileMapper.toResponse(saved);
    }

    //    GET BY ID

    @Override
    public ProfileResponse getById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        return profileMapper.toResponse(profile);
    }

    //    GET BY USER ID

    @Override
    public ProfileResponse getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return profileMapper.toResponse(user.getProfile());
    }

    //UPDATE BY ID
    @Transactional
    @Override
    public ProfileResponse updateById(Long id, ProfileRequest request) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        profileMapper.updateToEntity(request, profile);
        Profile saved = profileRepository.save(profile);
        return profileMapper.toResponse(saved);
    }

    //UPDATE BY USER ID
    @Transactional
    @Override
    public ProfileResponse updateByUsername(String username, ProfileRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return updateById(user.getProfile().getId(), request);
    }

    //DELETE
    @Transactional
    @Override
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

