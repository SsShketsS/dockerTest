//package com.example.dockerTest.service;
//
//
//import com.example.dockerTest.docker.entity.User;
//import com.example.dockerTest.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public User registerUser(String username, String password) {
//        if (userRepository.findByUsername(username).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password)); // Шифруем пароль
//        user.setRoles(Collections.singleton("ROLE_USER")); // По умолчанию роль USER
//
//        return userRepository.save(user);
//    }
//}