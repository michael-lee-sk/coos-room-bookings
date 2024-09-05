package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.BookingRequest;
import com.example.coosroombookings.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    public void testBookRoom() throws Exception {
        BookingRequest bookingRequest = new BookingRequest("user@example.com", "Conference Room",
                LocalDateTime.of(2024, 9, 5, 10, 0),
                LocalDateTime.of(2024, 9, 5, 12, 0));

        Mockito.doNothing().when(bookingService).bookRoom(Mockito.any(BookingRequest.class));

        mockMvc.perform(post("/api/rooms/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"user@example.com\", \"roomName\": \"Conference Room\", " +
                                "\"startTime\": \"2024-09-05T10:00:00\", \"endTime\": \"2024-09-05T12:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Room booked successfully"));
    }
}
