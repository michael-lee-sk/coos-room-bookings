
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
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room) {
        Room savedRoom = roomService.createRoom(room);
        return ResponseEntity.status(201).body(savedRoom);
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return roomService.getAvailableRooms(startDate, endDate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.findRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room updatedRoom) {
        Room room = roomService.updateRoom(id, updatedRoom);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Room>> searchRooms(@RequestParam String name, @RequestParam int minCapacity) {
        List<Room> rooms = roomService.searchRooms(name, minCapacity);
        return ResponseEntity.ok(rooms);
    }
}
