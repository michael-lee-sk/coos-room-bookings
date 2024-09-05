package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.BookingRequest;
import com.example.coosroombookings.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookRoom(@RequestBody BookingRequest request) {
        try {
            // Use the BookingService to handle the booking and Google Calendar event
            boolean success = bookingService.bookRoom(request);

            if (success) {
                return ResponseEntity.ok("Room booked successfully and added to your Google Calendar.");
            } else {
                return ResponseEntity.status(500).body("Error booking room.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error booking room: " + e.getMessage());
        }
    }
}
