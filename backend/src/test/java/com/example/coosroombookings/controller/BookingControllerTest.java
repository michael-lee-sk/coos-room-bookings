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

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.security.test.context.support.WithMockUser;


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

        // Mock the behavior of bookingService.bookRoom() to return true
        when(bookingService.bookRoom(any(BookingRequest.class)))
                .thenReturn(true);  // Assuming it returns true if booking is successful

        mockMvc.perform(post("/api/rooms/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"user@example.com\", \"roomName\": \"Conference Room\", " +
                                "\"startTime\": \"2024-09-05T10:00:00\", \"endTime\": \"2024-09-05T12:00:00\"}"))
                .andExpect(status().isOk())  // Expecting status 200 OK
                .andExpect(content().string("Room booked successfully"));
    }
}
