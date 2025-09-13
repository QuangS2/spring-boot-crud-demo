package com.example.demoWeb.controller;

import com.example.demoWeb.dto.UserProfileCreateRequest;
import com.example.demoWeb.dto.UserProfileResponse;
import com.example.demoWeb.dto.UserProfileUpdateRequest;
import com.example.demoWeb.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final UserProfileService profileService;

    //    CREAT
    @PostMapping
    public ResponseEntity<UserProfileResponse> create(@Valid @RequestBody UserProfileCreateRequest request) {
        UserProfileResponse response = profileService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getById(@PathVariable Long id) {
        UserProfileResponse response = profileService.getById(id);
        return ResponseEntity.ok(response);
    }

    //GET BY ID USER
    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileResponse> getByUserId(@PathVariable Long id) {
        UserProfileResponse response = profileService.getById(id);
        return ResponseEntity.ok(response);
    }

    //UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> updateProfileById(@PathVariable Long id, @Valid @RequestBody UserProfileUpdateRequest request) {
        UserProfileResponse response = profileService.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    //UPDATE BY USER ID
    @PutMapping("/user/{id}")
    public ResponseEntity<UserProfileResponse> updateProfileByUserId(@PathVariable Long id, @Valid @RequestBody UserProfileUpdateRequest request) {
        UserProfileResponse response = profileService.updateByUserId(id, request);
        return ResponseEntity.ok(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
