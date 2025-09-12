package com.example.demoWeb.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demoWeb.model.User;
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Spring Boot!";
    }
    @GetMapping("/hello/{name}")
    public String sayHelloTo(@PathVariable String name){
        return "Hello, "+ name+"!";
    }
//    @PostMapping("/user")
//    public String createUser(@RequestBody User user){
//        return "User created: " + user.getName()+", age "+user.getAge();
//    }
}
