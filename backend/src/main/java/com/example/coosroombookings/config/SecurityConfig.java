package com.example.coosroombookings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()  // Enable CORS
                .csrf().disable()  // Disable CSRF for now
                .authorizeRequests()
                .antMatchers("/", "/oauth2/**", "/error").permitAll()  // Allow public access
                .anyRequest().authenticated()  // Protect all other routes
                .and()
                .oauth2Login()
                .defaultSuccessUrl("http://localhost:3000/home", true)  // Redirect to frontend after login
                .and()
                .logout()
                .logoutSuccessUrl("http://localhost:3000/")  // Redirect to frontend after logout
                .permitAll();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");  // Allow requests from frontend
        configuration.addAllowedMethod("*");  // Allow all HTTP methods
        configuration.addAllowedHeader("*");  // Allow all headers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
