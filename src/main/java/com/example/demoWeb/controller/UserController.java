package com.example.demoWeb.controller;

import com.example.demoWeb.dto.UserResponse;
import com.example.demoWeb.dto.UserCreateRequest;
import com.example.demoWeb.dto.UserUpdateRequest;
import com.example.demoWeb.exception.UserNotFoundException;
import com.example.demoWeb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // endpoint gốc
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    CREATE
    @PostMapping
    public ResponseEntity<UserResponse> creater(@Valid @RequestBody UserCreateRequest request) {

        UserResponse reponse = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);

    }

    //    READ ALL
    @GetMapping
    public ResponseEntity<List<UserResponse>> gerAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    //    READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    //    UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {

        return userService.updateUser(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //    DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
