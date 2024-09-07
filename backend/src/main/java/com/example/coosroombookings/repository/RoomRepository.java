package com.example.coosroombookings.repository;

import com.example.coosroombookings.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByNameContainingAndCapacityGreaterThanEqual(String name, int capacity);
}
