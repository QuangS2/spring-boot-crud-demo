package com.example.demoWeb.controller;

import com.example.demoWeb.dto.ProfileCreateRequest;
import com.example.demoWeb.dto.ProfileResponse;
import com.example.demoWeb.dto.ProfileUpdateRequest;
import com.example.demoWeb.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    //    CREAT
    @PostMapping
    public ResponseEntity<ProfileResponse> create(@Valid @RequestBody ProfileCreateRequest request) {
        ProfileResponse response = profileService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getById(@PathVariable Long id) {
        ProfileResponse response = profileService.getById(id);
        return ResponseEntity.ok(response);
    }

    //GET BY ID USER
    @GetMapping("/user/{id}")
    public ResponseEntity<ProfileResponse> getByUserId(@PathVariable Long id) {
        ProfileResponse response = profileService.getByUserId(id);
        return ResponseEntity.ok(response);
    }

    //UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfileById(@PathVariable Long id, @Valid @RequestBody ProfileUpdateRequest request) {
        ProfileResponse response = profileService.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    //UPDATE BY USER ID
    @PutMapping("/user/{id}")
    public ResponseEntity<ProfileResponse> updateProfileByUserId(@PathVariable Long id, @Valid @RequestBody ProfileUpdateRequest request) {
        ProfileResponse response = profileService.updateByUserId(id, request);
        return ResponseEntity.ok(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
