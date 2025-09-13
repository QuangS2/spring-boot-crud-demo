package com.example.demoWeb.service;

import com.example.demoWeb.dto.UserProfileCreateRequest;
import com.example.demoWeb.dto.UserProfileResponse;
import com.example.demoWeb.dto.UserProfileUpdateRequest;

import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.model.User;
import com.example.demoWeb.model.UserProfile;
import com.example.demoWeb.repository.UserProfileRepository;
import com.example.demoWeb.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    private final UserRepository userRepository;

    //    CREAT
    public UserProfileResponse create(UserProfileCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        if (user.getProfile() != null) throw new RuntimeException("This User has profile already");
        UserProfile profile = UserProfile.builder()
                .address(request.getAddress())
                .phone(request.getPhone())
                .bio(request.getBio())
                .user(user)
                .build();
        UserProfile saved = userProfileRepository.save(profile);
        return UserProfileResponse.builder()
                .id(saved.getId())
                .address(saved.getAddress())
                .phone(saved.getPhone())
                .userName(saved.getUser().getName())
                .build();
    }

    //    GET BY ID
    public UserProfileResponse getById(Long id) {

        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id " + id));
        return UserProfileResponse.builder()
                .id(profile.getId())
                .address(profile.getAddress())
                .phone(profile.getPhone())
                .userName(profile.getUser().getName())
                .build();
    }

    //    GET BY USER ID
    public UserProfileResponse getByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return getById(user.getProfile().getId());
    }

    //UPDATE BY ID
    public UserProfileResponse updateById(Long id, UserProfileUpdateRequest request) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id " + id));
        profile.setAddress(request.getAddress());
        profile.setPhone(request.getPhone());
        profile.setBio(request.getBio());
        UserProfile saved = userProfileRepository.save(profile);
        return UserProfileResponse.builder()
                .id(saved.getId())
                .address(saved.getAddress())
                .phone(saved.getPhone())
                .bio(saved.getBio())
                .userName(saved.getUser().getName())
                .build();
    }

    //UPDATE BY USER ID
    public UserProfileResponse updateByUserId(Long id, UserProfileUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return updateById(user.getProfile().getId(), request);
    }

    //DELETE
    public void deleteProfile(Long id) {

        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id " + id));

        User user = profile.getUser();
        if (user != null) {
            user.setProfile(null); // gỡ ràng buộc
            userRepository.save(user);
        }
        userProfileRepository.deleteById(id);
    }
}

