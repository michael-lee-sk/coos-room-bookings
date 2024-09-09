package com.example.coosroombookings.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Map<String, Object> response = new HashMap<>();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            response.put("status", statusCode);

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                response.put("error", "404 Not Found");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                response.put("error", "500 Internal Server Error");
            } else {
                response.put("error", "Unexpected error occurred");
            }
        } else {
            response.put("error", "Unknown error occurred");
        }

        return new ResponseEntity<>(response, HttpStatus.valueOf((Integer) status));
    }
}


