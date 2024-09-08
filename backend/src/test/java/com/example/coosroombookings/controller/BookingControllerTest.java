package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.BookingRequest;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.model.User;
import com.example.coosroombookings.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @WithMockUser(roles = "USER")
    @Test
    public void testCreateBooking() throws Exception {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);
        BookingRequest bookingRequest = new BookingRequest(room, Collections.singletonList(user),
                LocalDateTime.of(2024, 9, 1, 10, 0), LocalDateTime.of(2024, 9, 2, 10, 0));

        Booking booking = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"room\": {\"id\": 1, \"name\": \"Conference Room\", \"capacity\": 10}, " +
                                "\"users\": [{\"username\": \"testuser\", \"email\": \"testuser@example.com\"}], " +
                                "\"startTime\": \"2024-09-01T10:00:00\", \"endTime\": \"2024-09-02T10:00:00\"}")
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.room.id", is(1)))
                .andExpect(jsonPath("$.room.name", is("Conference Room")))
                .andExpect(jsonPath("$.startDate", is("2024-09-01")))
                .andExpect(jsonPath("$.endDate", is("2024-09-02")));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testGetBookings() throws Exception {
        Room room1 = new Room(1L, "Conference Room", 10);
        User user1 = new User("testuser1", "password1", "testuser1@example.com", true);

        Room room2 = new Room(2L, "Meeting Room", 5);
        User user2 = new User("testuser2", "password2", "testuser2@example.com", true);

        Booking booking1 = new Booking(room1, user1, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));
        Booking booking2 = new Booking(room2, user2, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 2));

        when(bookingService.getAllBookings()).thenReturn(Arrays.asList(booking1, booking2));

        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].room.name", is("Conference Room")))
                .andExpect(jsonPath("$[0].startDate", is("2024-09-01")))
                .andExpect(jsonPath("$[0].endDate", is("2024-09-02")))
                .andExpect(jsonPath("$[1].room.name", is("Meeting Room")))
                .andExpect(jsonPath("$[1].startDate", is("2024-10-01")))
                .andExpect(jsonPath("$[1].endDate", is("2024-10-02")));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testDeleteBooking() throws Exception {
        doNothing().when(bookingService).deleteBookingById(1L);

        mockMvc.perform(delete("/api/bookings/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testGetBookingById() throws Exception {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);
        Booking booking = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        when(bookingService.getBookingById(1L)).thenReturn(Optional.of(booking));

        mockMvc.perform(get("/api/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.room.name", is("Conference Room")))
                .andExpect(jsonPath("$.startDate", is("2024-09-01")))
                .andExpect(jsonPath("$.endDate", is("2024-09-02")));
    }
}
