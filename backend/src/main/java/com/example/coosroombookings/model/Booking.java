package com.example.coosroombookings.model;

import java.time.LocalDate;

public class Booking {
    private Long id;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(Long id, Long roomId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking() {}

    // Getters and setters
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
}
