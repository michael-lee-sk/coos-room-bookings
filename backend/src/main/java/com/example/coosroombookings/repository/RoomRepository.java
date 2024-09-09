package com.example.coosroombookings.repository;

import com.example.coosroombookings.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Find rooms based on name and capacity
    List<Room> findByNameContainingAndCapacityGreaterThanEqual(String name, int capacity);

    // Query to find available rooms that are not booked in the specified time range
    @Query("SELECT r FROM Room r WHERE r.id NOT IN " +
            "(SELECT b.room.id FROM Booking b WHERE b.startTime < :endDate AND b.endTime > :startDate)")
    List<Room> findAvailableRooms(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}