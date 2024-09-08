package com.example.coosroombookings.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
        Booking mockBooking = new Booking();
        mockBooking.setId(1L);

        when(bookingService.createBooking(any(Booking.class))).thenReturn(mockBooking);

        ResponseEntity<Booking> response = bookingController.createBooking(mockBooking);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(bookingService).createBooking(any(Booking.class));
    }

    @Test
    public void testCreateBooking_NullInput() {
        ResponseEntity<Booking> response = bookingController.createBooking(null);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testGetAllBookings() {
        List<Booking> mockBookings = Arrays.asList(
                new Booking(),
                new Booking()
        );

        when(bookingService.getAllBookings()).thenReturn(mockBookings);

        List<Booking> bookings = bookingController.getAllBookings();

        assertEquals(2, bookings.size());
        verify(bookingService).getAllBookings();
    }

    @Test
    public void testGetBookingById() {
        Booking mockBooking = new Booking();
        mockBooking.setId(1L);

        when(bookingService.getBookingById(1L)).thenReturn(Optional.of(mockBooking));

        ResponseEntity<Booking> response = bookingController.getBookingById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBooking.getId(), response.getBody().getId());
        verify(bookingService).getBookingById(1L);
    }

    @Test
    public void testGetBookingById_NotFound() {
        when(bookingService.getBookingById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Booking> response = bookingController.getBookingById(999L);

        assertEquals(404, response.getStatusCodeValue());
        verify(bookingService).getBookingById(999L);
    }

    @Test
    public void testDeleteBookingById() {
        doNothing().when(bookingService).deleteBookingById(1L);

        ResponseEntity<Void> response = bookingController.deleteBookingById(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(bookingService).deleteBookingById(1L);
    }

    @Test
    public void testDeleteBookingById_NotFound() {
        doThrow(new RuntimeException("Booking not found")).when(bookingService).deleteBookingById(999L);

        ResponseEntity<Void> response = bookingController.deleteBookingById(999L);

        assertEquals(404, response.getStatusCodeValue());
        verify(bookingService).deleteBookingById(999L);
    }
}
