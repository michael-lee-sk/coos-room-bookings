package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Create a room
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room savedRoom = roomService.createRoom(room);
        return ResponseEntity.status(201).body(savedRoom);
    }

    // Get all available rooms
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // Get a room by ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.findRoomById(id);
        return ResponseEntity.ok(room);
    }

    // Update room details
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        Room room = roomService.updateRoom(id, updatedRoom);
        return ResponseEntity.ok(room);
    }

    // Delete a room
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }

    // Search rooms by name and minimum capacity
    @GetMapping("/search")
    public ResponseEntity<List<Room>> searchRooms(@RequestParam String name, @RequestParam int minCapacity) {
        List<Room> rooms = roomService.searchRooms(name, minCapacity);
        return ResponseEntity.ok(rooms);
    }
}
