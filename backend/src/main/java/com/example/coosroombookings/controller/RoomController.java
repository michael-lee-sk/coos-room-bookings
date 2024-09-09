package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        logger.info("Received request for available rooms from {} to {}", startTime, endTime);

        if (startTime == null || endTime == null) {
            startTime = LocalDateTime.now();
            endTime = startTime.plusHours(1);
        }

        List<Room> availableRooms = roomService.getAvailableRooms(startTime, endTime);
        logger.info("Found {} available rooms", availableRooms.size());

        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/next-available")
    public ResponseEntity<Room> findNextAvailableRoom(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        logger.info("Received request for next available room from {} to {}", startTime, endTime);

        Room nextAvailableRoom = roomService.findNextAvailableRoom(startTime, endTime);

        if (nextAvailableRoom == null) {
            return ResponseEntity.noContent().build(); // No room found
        }

        return ResponseEntity.ok(nextAvailableRoom);
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
