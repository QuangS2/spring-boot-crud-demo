package com.example.demoWeb.admin.controller;

import com.example.demoWeb.account.dto.request.UserRegisterRequest;
import com.example.demoWeb.account.dto.request.UserUpdateRequest;
import com.example.demoWeb.account.dto.response.UserResponse;
import com.example.demoWeb.account.service.UserService;
import com.example.demoWeb.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class UserAdminController {
    private final UserService userService;

    
    // /users get all user
    @GetMapping
    public ResponseEntity<List<UserResponse>> gerAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    // users/id get 1 user
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // post users create user
    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        UserResponse reponse = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);

    }

    // put user/id update user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {

        return userService.updateUser(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //delete user/id deleteuser
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
