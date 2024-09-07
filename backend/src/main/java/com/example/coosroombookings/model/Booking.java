package com.example.coosroombookings.model;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {

    private Long id;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    // No-argument constructor (required for deserialization)
    public Booking() {
    }

    // All-argument constructor
    public Booking(Long id, Long roomId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Custom equals method that compares fields instead of object references
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(roomId, booking.roomId) &&
                Objects.equals(startDate, booking.startDate) &&
                Objects.equals(endDate, booking.endDate);
    }

    // Custom hashCode method that generates a hash based on fields
    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, startDate, endDate);
    }
}
