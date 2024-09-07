package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking1;
    private Booking booking2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        booking1 = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));
        booking2 = new Booking(2L, 2L, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 2));
    }

    @Test
    public void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking1);

        Booking createdBooking = bookingService.createBooking(booking1);

        assertNotNull(createdBooking);
        assertEquals(1L, createdBooking.getId());
        verify(bookingRepository, times(1)).save(booking1);
    }

    @Test
    public void testGetAllBookings() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        assertEquals(2, bookingService.getAllBookings().size());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookingById() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking1));

        Optional<Booking> foundBooking = bookingService.getBookingById(1L);

        assertTrue(foundBooking.isPresent());
        assertEquals(1L, foundBooking.get().getId());
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBookingById(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }
}
