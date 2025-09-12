package com.example.demoWeb.controller;

import com.example.demoWeb.dto.UserResponse;
import com.example.demoWeb.dto.UserCreateRequest;
//import com.example.demoWeb.repository.UserRepository;
import com.example.demoWeb.dto.UserUpdateRequest;
import com.example.demoWeb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // endpoint gốc
public class UserController {
//    private List<User> users = new ArrayList<>();

//    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    public UserController(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//    CREATE
    @PostMapping
    public ResponseEntity<UserResponse> creater(@Valid @RequestBody UserCreateRequest request) {
//        users.add(user);
//        return user;

//        return userRepository.save(user);

//        User saveUser = userService.createUser(user);
//        return ResponseEntity.ok(saveUser);

        UserResponse reponse = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);

    }

    //    READ ALL
    @GetMapping
    public ResponseEntity<List<UserResponse>> gerAllUsers() {
//        return users;

//        return userRepository.findAll();

        return ResponseEntity.ok(userService.getAllUsers());
    }

    //    READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
//        return users.stream()
//                .filter(u -> u.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//        return userRepository.findById(id).orElse(null);

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //    UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
//        for (User u : users){
//            if (u.getId().equals(id)){
//                u.setName(newUser.getName());
//                u.setAge(newUser.getAge());
//                return  u;
//            }
//        }
//        return null;

//        return userRepository.findById(id)
//                .map(u->{
//                    u.setName(newUser.getName());
//                    u.setAge(newUser.getAge());
//                    return userRepository.save(u);
//                })
//                .orElse(null);

//        return userService.updateUser(id,newUser)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

        return userService.updateUser(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //    DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        users.removeIf(u -> u.getId().equals(id));
//        return  "User with id "+id+"deleted.";

//        if (userRepository.existsById(id)){
//            userRepository.deleteById(id);
//            return true;
//        }
//        return false;

        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
