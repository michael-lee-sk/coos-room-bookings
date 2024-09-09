package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.model.User;
import com.example.coosroombookings.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
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

        Room room1 = new Room(1L, "Conference Room", 10);
        User user1 = new User("testuser1", "password1", "testuser1@example.com", true);

        Room room2 = new Room(2L, "Meeting Room", 5);
        User user2 = new User("testuser2", "password2", "testuser2@example.com", true);

        booking1 = new Booking(room1, user1, LocalDateTime.of(2024, 9, 1, 10, 0), LocalDateTime.of(2024, 9, 1, 12, 0));
        booking2 = new Booking(room2, user2, LocalDateTime.of(2024, 10, 1, 10, 0), LocalDateTime.of(2024, 10, 1, 12, 0));
    }

    @Test
    public void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking1);

        Booking createdBooking = bookingService.createBooking(booking1);

        assertNotNull(createdBooking);
        assertEquals(booking1.getId(), createdBooking.getId());
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
        assertEquals(booking1.getId(), foundBooking.get().getId());
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBookingById(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }
}
