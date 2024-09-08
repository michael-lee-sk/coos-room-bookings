package com.example.coosroombookings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for testing purposes
                .authorizeRequests()
                .antMatchers("/", "/login", "/oauth2/**", "/error", "/home").permitAll()  // Allow access to landing pages, login, etc.
                .anyRequest().authenticated()  // Protect other endpoints (e.g., room bookings)
                .and()
                .oauth2Login()
                .loginPage("/login")  // Redirect to custom login page if unauthenticated
                .defaultSuccessUrl("/home", true)  // After successful login, redirect to /home
                .and()
                .headers().frameOptions().disable();  // Optional: Disable frame options for H2 console (if needed)

        return http.build();
    }

    // Handler for successful authentication (optional, but useful for handling post-login redirects)
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/home");
    }
}
