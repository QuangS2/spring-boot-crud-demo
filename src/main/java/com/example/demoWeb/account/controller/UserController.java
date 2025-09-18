package com.example.demoWeb.account.controller;

import com.example.demoWeb.account.dto.response.UserResponse;
import com.example.demoWeb.account.dto.request.UserUpdateRequest;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.account.service.UserService;
import com.example.demoWeb.profile.dto.request.ProfileRequest;
import com.example.demoWeb.profile.dto.response.ProfileResponse;
import com.example.demoWeb.profile.service.ProfileService;
import com.example.demoWeb.security.CustomUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user") // endpoint gốc
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final ProfileService profileService;

    //getprofile
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal UserDetails userDetail) {
        UserResponse response = userService.getCurrentUserByUsername(userDetail.getUsername());
        return ResponseEntity.ok(response);
    }

    //get profile
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@AuthenticationPrincipal UserDetails userDetail) {
        ProfileResponse response = profileService.getByUsername(userDetail.getUsername());
        return ResponseEntity.ok(response);

    }

    //put profile
    @PutMapping("/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@AuthenticationPrincipal UserDetails userDetail, @Valid @RequestBody ProfileRequest request) {
        ProfileResponse response = profileService.updateByUsername(userDetail.getUsername(), request);
        return ResponseEntity.ok(response);
    }
    //put password
    //delete?


//    //USER - ASSIGN - ROLE
//
//    @PostMapping("/{userId}/roles/{roleName}")
//    public ResponseEntity<UserResponse> assignRole(@PathVariable Long userId, @PathVariable String roleName) {
//        return ResponseEntity.ok(userService.assignRole(userId, roleName));
//    }
//
//    //USER REMOVE ROLE
//    @DeleteMapping("/{userId}/roles/{roleName}")
//    public ResponseEntity<UserResponse> removeRoleFromUser(@PathVariable Long userId, @PathVariable String roleName) {
//        return ResponseEntity.ok(userService.removeRoleFromUser(userId, roleName));
//    }
}
