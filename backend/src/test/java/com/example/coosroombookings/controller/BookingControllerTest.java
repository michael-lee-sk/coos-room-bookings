package com.example.coosroombookings.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.coosroombookings.controller.BookingController;
import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.User;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.service.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBooking() {
        // Step 1: Mock User and Room objects
        User mockUser = new User();
        mockUser.setId(1L);
        Room mockRoom = new Room();
        mockRoom.setId(1L);

        // Step 2: Create Booking
        Booking booking = new Booking();
        booking.setUser(mockUser);
        booking.setRoom(mockRoom);
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now().plusDays(2));

        // Step 3: Stub service method
        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

        // Step 4: Call the controller method
        ResponseEntity<Booking> response = bookingController.createBooking(booking);

        // Step 5: Verify result
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockUser, response.getBody().getUser());
        assertEquals(mockRoom, response.getBody().getRoom());
        verify(bookingService).createBooking(any(Booking.class));
    }

    @Test
    public void testGetBookingById() {
        // Mock a Booking object
        User mockUser = new User();
        mockUser.setId(1L);
        Room mockRoom = new Room();
        mockRoom.setId(1L);
        Booking mockBooking = new Booking(mockRoom, mockUser, LocalDate.now(), LocalDate.now().plusDays(1));
        mockBooking.setId(1L);

        // Stub the service
        when(bookingService.getBookingById(1L)).thenReturn(Optional.of(mockBooking));

        // Call the controller method
        ResponseEntity<Booking> response = bookingController.getBookingById(1L);

        // Verify result
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(bookingService).getBookingById(1L);
    }

    @Test
    public void testGetAllBookings() {
        List<Booking> mockBookings = Arrays.asList(
                new Booking(new Room(1L, "Room A", 5), new User("John Doe", "1234","john@example.com", true), LocalDate.now(), LocalDate.now().plusDays(1)),
                new Booking(new Room(2L, "Room B", 10), new User("Jane Doe", "123", "jane@example.com", true), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2))
        );

        // Stub the service method
        when(bookingService.getAllBookings()).thenReturn(mockBookings);

        // Call the controller method
        List<Booking> bookings = bookingController.getAllBookings();

        // Verify result
        assertEquals(2, bookings.size());
        verify(bookingService).getAllBookings();
    }

    @Test
    public void testDeleteBooking() {
        // Stub service delete method
        doNothing().when(bookingService).deleteBookingById(1L);

        // Call the controller method
        ResponseEntity<Void> response = bookingController.deleteBookingById(1L);

        // Verify deletion
        assertEquals(204, response.getStatusCodeValue());
        verify(bookingService).deleteBookingById(1L);
    }
}
