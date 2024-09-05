package com.example.coosroombookings.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BookingRequest {
    // Getters and Setters
    private String email;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
