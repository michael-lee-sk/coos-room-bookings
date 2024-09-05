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

    public BookingRequest(String email, String roomName, LocalDateTime startTime, LocalDateTime endTime) {
        this.email = email;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
