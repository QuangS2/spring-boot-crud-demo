package com.example.demoWeb.profile.controller;

import com.example.demoWeb.profile.dto.request.ProfileRequest;
import com.example.demoWeb.profile.dto.response.ProfileResponse;
import com.example.demoWeb.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/profiles")
public class ProfileController {

    private final ProfileService profileService;

    //    CREAT (generate ID)
    @PostMapping
    public ResponseEntity<ProfileResponse> create(@Valid @RequestBody ProfileRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        ProfileResponse response = profileService.create(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //GET BY ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ProfileResponse> getById(@PathVariable Long id) {
//        ProfileResponse response = profileService.getById(id);
//        return ResponseEntity.ok(response);
//    }

    //GET BY ID USERNAME
    @GetMapping
    public ResponseEntity<ProfileResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails) {
        ProfileResponse response = profileService.getByUsername(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    //UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfileById(@PathVariable Long id, @Valid @RequestBody ProfileRequest request) {
        ProfileResponse response = profileService.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    //UPDATE BY USERNAME
    @PutMapping
    public ResponseEntity<ProfileResponse> updateProfileByUserId(@Valid @RequestBody ProfileRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        ProfileResponse response = profileService.updateByUsername(userDetails.getUsername(), request);
        return ResponseEntity.ok(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
