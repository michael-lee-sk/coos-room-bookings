package com.example.coosroombookings.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class BookingRequest {
    private Room room; // Include a Room
    private List<User> users; // Include a List of Users
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor
    public BookingRequest(Room room, List<User> users, LocalDateTime startTime, LocalDateTime endTime) {
        this.room = room;
        this.users = users;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
