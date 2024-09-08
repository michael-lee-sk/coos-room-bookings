package com.example.coosroombookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.coosroombookings.repository")
@EntityScan(basePackages = "com.example.coosroombookings.model")
public class CoosRoomBookingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoosRoomBookingsApplication.class, args);
    }
}
