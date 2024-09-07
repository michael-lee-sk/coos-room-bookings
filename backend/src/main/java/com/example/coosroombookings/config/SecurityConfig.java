package com.example.coosroombookings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Forcefully disable CSRF to prevent 403 errors
                .authorizeRequests().anyRequest().permitAll()  // Allow all traffic without authentication or authorization
                .and()
                .headers().frameOptions().disable();  // Optional: for H2 console

        return http.build();
    }
}
