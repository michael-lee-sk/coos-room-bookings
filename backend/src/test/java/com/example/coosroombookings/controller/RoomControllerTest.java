package com.example.coosroombookings.controller;

import com.example.coosroombookings.config.TestSecurityConfig;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    // Test getting available rooms
    @Test
    public void testGetAvailableRooms() throws Exception {
        Room room1 = new Room(1L, "Conference Room", 10);
        Room room2 = new Room(2L, "Meeting Room", 5);

        when(roomService.getAvailableRooms()).thenReturn(Arrays.asList(room1, room2));

        mockMvc.perform(get("/api/rooms/available"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Conference Room")))
                .andExpect(jsonPath("$[1].name", is("Meeting Room")));
    }

    // Test creating a room
    @Test
    public void testAddRoom() throws Exception {
        Room room = new Room(1L, "New Room", 8);

        when(roomService.createRoom(any(Room.class))).thenReturn(room);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Room\", \"capacity\": 8}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("New Room")))
                .andExpect(jsonPath("$.capacity", is(8)));
    }


    // Test updating a room
    @Test
    public void testUpdateRoom() throws Exception {
        Room room = new Room(1L, "Updated Room", 12);

        when(roomService.updateRoom(Mockito.eq(1L), any(Room.class))).thenReturn(room);

        mockMvc.perform(put("/api/rooms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Room\", \"capacity\": 12}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Room")))
                .andExpect(jsonPath("$.capacity", is(12)));
    }


    // Test deleting a room
    @Test
    public void testDeleteRoom() throws Exception {
        doNothing().when(roomService).deleteRoomById(1L);

        mockMvc.perform(delete("/api/rooms/1"))
                .andExpect(status().isNoContent());
    }


    // Test searching rooms
    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void testSearchRooms() throws Exception {
        Room room1 = new Room(1L, "Conference Room", 10);
        Room room2 = new Room(2L, "Meeting Room", 5);

        when(roomService.searchRooms(Mockito.eq("Room"), Mockito.eq(5)))
                .thenReturn(Arrays.asList(room1, room2));

        mockMvc.perform(get("/api/rooms/search")
                        .param("name", "Room")
                        .param("minCapacity", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Conference Room")))
                .andExpect(jsonPath("$[1].name", is("Meeting Room")));
    }
}
