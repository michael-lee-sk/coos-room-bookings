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
                .csrf().disable()  // Disable CSRF for testing purposes (enable this in production)
                .authorizeRequests()
                .antMatchers("/", "/login", "/oauth2/**", "/error", "/home").permitAll()  // Allow public access to these routes
                .anyRequest().authenticated()  // Protect other endpoints
                .and()
                .oauth2Login()
                .loginPage("/login")  // Redirect to this page for login
                .defaultSuccessUrl("/home", true)  // After successful login, redirect to /home
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  // Redirect to login page after logout
                .and()
                .headers().frameOptions().disable();  // Disable frame options if needed for H2 console

        return http.build();
    }

    // Optional: Redirect to a specific URL after successful login
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/home");
    }
}
