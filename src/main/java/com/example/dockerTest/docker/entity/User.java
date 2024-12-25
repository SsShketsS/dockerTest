//package com.example.dockerTest.docker.entity;
//import jakarta.persistence.*;
//import java.util.Set;
//
//
//@Entity
//public class User {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String username; // Имя пользователя
//
//    @Column(nullable = false)
//    private String password; // Пароль (в зашифрованном виде)
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    private Set<String> roles; // Роли пользователя (например, ROLE_USER, ROLE_ADMIN)
//
//
//
//
//    public Set<String> getRoles() {
//        return roles;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setRoles(Set<String> roles) {
//        this.roles = roles;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//}
