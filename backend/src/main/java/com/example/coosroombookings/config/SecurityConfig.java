package com.example.coosroombookings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (isTestProfileActive()) {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll();  // Disable security in test profile
        } else {
            http
                    .authorizeRequests()
                    .antMatchers("/", "/login**", "/css/**", "/js/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/rooms/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/rooms/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/rooms/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/rooms", true);
        }
        return http.build();
    }

    private boolean isTestProfileActive() {
        return environment.getActiveProfiles() != null &&
                environment.getActiveProfiles().length > 0 &&
                "test".equals(environment.getActiveProfiles()[0]);
    }
}
