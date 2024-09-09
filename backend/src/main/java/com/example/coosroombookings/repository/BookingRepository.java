package com.example.coosroombookings.repository;

import com.example.coosroombookings.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Query to find bookings that overlap with the given time range for a specific room
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.startTime < :endDate AND b.endTime > :startDate")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
