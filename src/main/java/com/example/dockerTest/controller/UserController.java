//package com.example.dockerTest.controller;
//
//import com.example.dockerTest.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    // Регистрация пользователя
//    @PostMapping("/register")
//    public String registerUser(@RequestParam String username, @RequestParam String password) {
//        userService.registerUser(username, password);
//        return "User registered successfully";
//    }
//
//    // Страница входа (можно реализовать через Spring Security)
//    @GetMapping("/login")
//    public String loginPage() {
//        return "Login page (handled by Spring Security)";
//    }
//}