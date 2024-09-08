package com.example.coosroombookings.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "error";  // Make sure you have 'error.html' in 'src/main/resources/templates'
    }

    // The getErrorPath method is no longer necessary, so it can be removed.
}
