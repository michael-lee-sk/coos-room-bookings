package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room) {
        if (room == null) {
            return ResponseEntity.badRequest().build();  // Return 400 if input is null
        }
        Room savedRoom = roomService.createRoom(room);
        return ResponseEntity.status(201).body(savedRoom);
    }

    // Updated endpoint for available rooms
    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate
    ) {
        // If no start and end dates are provided, check current availability
        if (startDate == null || endDate == null) {
            startDate = LocalDateTime.now();
            endDate = startDate.with(LocalTime.MAX); // Default to the end of the current day
        }
        return roomService.getAvailableRooms(startDate, endDate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Optional<Room> room = Optional.ofNullable(roomService.findRoomById(id));
        if (room.isEmpty()) {
            return ResponseEntity.status(404).build();  // Return 404 if room not found
        }
        return ResponseEntity.ok(room.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room updatedRoom) {
        Room room = roomService.updateRoom(id, updatedRoom);
        if (room == null) {
            return ResponseEntity.status(404).build();  // Return 404 if room not found for update
        }
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoomById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();  // Return 404 if room not found for deletion
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Room>> searchRooms(@RequestParam String name, @RequestParam int minCapacity) {
        List<Room> rooms = roomService.searchRooms(name, minCapacity);
        return ResponseEntity.ok(rooms);
    }
}
