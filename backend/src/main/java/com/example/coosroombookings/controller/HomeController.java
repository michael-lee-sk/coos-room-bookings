package com.example.coosroombookings.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to the Home Page!";  // This message will be fetched by the frontend
    }
}
