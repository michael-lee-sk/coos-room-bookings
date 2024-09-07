package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.Booking;
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
import java.util.Arrays;
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

    // Test to create a booking with fixed dates
    @WithMockUser(roles = "USER")
    @Test
    public void testCreateBooking() throws Exception {
        // Using fixed dates to ensure test consistency
        Booking booking = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roomId\": 1, \"startDate\": \"2024-09-01\", \"endDate\": \"2024-09-02\"}")
                        .with(csrf()))  // Adding CSRF token for POST request
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roomId", is(1)))
                .andExpect(jsonPath("$.startDate", is("2024-09-01")))
                .andExpect(jsonPath("$.endDate", is("2024-09-02")));
    }

    // Test to get all bookings
    @WithMockUser(roles = "USER")
    @Test
    public void testGetBookings() throws Exception {
        Booking booking1 = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));
        Booking booking2 = new Booking(2L, 2L, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 2));

        when(bookingService.getAllBookings()).thenReturn(Arrays.asList(booking1, booking2));

        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].roomId", is(1)))
                .andExpect(jsonPath("$[0].startDate", is("2024-09-01")))
                .andExpect(jsonPath("$[0].endDate", is("2024-09-02")))
                .andExpect(jsonPath("$[1].roomId", is(2)))
                .andExpect(jsonPath("$[1].startDate", is("2024-10-01")))
                .andExpect(jsonPath("$[1].endDate", is("2024-10-02")));
    }

    // Test to delete a booking
    @WithMockUser(roles = "USER")
    @Test
    public void testDeleteBooking() throws Exception {
        doNothing().when(bookingService).deleteBookingById(1L);

        mockMvc.perform(delete("/api/bookings/1")
                        .with(csrf()))  // Adding CSRF token for DELETE request
                .andExpect(status().isNoContent());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testGetBookingById() throws Exception {
        Booking booking = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        when(bookingService.getBookingById(1L)).thenReturn(Optional.of(booking));

        mockMvc.perform(get("/api/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomId", is(1)))
                .andExpect(jsonPath("$.startDate", is("2024-09-01")))
                .andExpect(jsonPath("$.endDate", is("2024-09-02")));
    }
}
