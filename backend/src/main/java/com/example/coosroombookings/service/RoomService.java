package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAvailableRooms() {
        return roomRepository.findAll();  // Example, adjust based on room availability logic
    }
}
