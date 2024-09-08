package com.example.coosroombookings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";  // Ensure you have a 'home.html' file in 'src/main/resources/templates'
    }
}
