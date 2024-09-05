
package com.example.coosroombookings.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    // Getters and Setters
    // Add other necessary fields for booking
    private String email;

    public Booking(Long id, String roomName, LocalDateTime startTime, LocalDateTime endTime, String email) {
        this.id = id;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.email = email;
    }

    public Booking() {}
}
