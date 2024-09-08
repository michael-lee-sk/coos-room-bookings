package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create a room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get available rooms based on a date range
    public List<Room> getAvailableRooms(LocalDateTime startDate, LocalDateTime endDate) {
        List<Room> allRooms = roomRepository.findAll();

        // Filter rooms based on availability during the specified date range
        return allRooms.stream()
                .filter(room -> room.isAvailableDuring(startDate, endDate))
                .collect(Collectors.toList());
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
