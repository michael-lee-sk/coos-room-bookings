package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.repository.BookingRepository;
import com.example.coosroombookings.repository.RoomRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    // Create a room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get available rooms based on a date range
    public List<Room> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        // Log the times being used for the query
        logger.info("Fetching available rooms from {} to {}", startTime, endTime);

        List<Room> availableRooms = roomRepository.findAvailableRooms(startTime, endTime);

        // Log the rooms retrieved
        logger.info("Found {} rooms available", availableRooms.size());

        return availableRooms;
    }


    // Get a room by ID
    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    // Update room details
    public Room updateRoom(Long id, Room updatedRoom) {
        Room room = findRoomById(id);  // Check if room exists
        room.setName(updatedRoom.getName());
        room.setCapacity(updatedRoom.getCapacity());
        return roomRepository.save(room);
    }

    // Delete a room
    public void deleteRoomById(Long id) {
        Room room = findRoomById(id);
        roomRepository.delete(room);
    }

    // Search rooms by name and capacity
    public List<Room> searchRooms(String name, int minCapacity) {
        return roomRepository.findByNameContainingAndCapacityGreaterThanEqual(name, minCapacity);
    }
}
