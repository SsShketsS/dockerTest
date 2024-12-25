//package com.example.dockerTest.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Отключаем CSRF (если не используется)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/register", "/login").permitAll() // Открытые маршруты
//                        .anyRequest().authenticated() // Все остальные маршруты требуют аутентификации
//                )
//                .formLogin(form -> form // Настройка формы входа
//                        .loginPage("/login") // Указываем свою страницу входа (опционально)
//                        .permitAll()
//                )
//                .logout(logout -> logout.permitAll()); // Разрешаем выход из системы
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//
//
