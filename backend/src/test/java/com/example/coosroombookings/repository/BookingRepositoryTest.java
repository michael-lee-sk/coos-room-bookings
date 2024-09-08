package com.example.coosroombookings.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.User;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.repository.BookingRepository;
import com.example.coosroombookings.repository.UserRepository;
import com.example.coosroombookings.repository.RoomRepository;
import com.example.coosroombookings.service.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;

public class BookingRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("John Doe");
        mockUser.setEmail("john.doe@example.com");

        Room mockRoom = new Room();
        mockRoom.setId(1L);
        mockRoom.setName("Room A");

        Booking mockBooking = new Booking(mockRoom, mockUser, LocalDate.now(), LocalDate.now().plusDays(1));
        mockBooking.setId(1L);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(mockBooking));

        Optional<Booking> foundBooking = bookingService.getBookingById(1L);

        assertTrue(foundBooking.isPresent());
        assertEquals(1L, foundBooking.get().getId());
    }

    @Test
    public void testSaveBooking() {
        User mockUser = new User();
        mockUser.setId(1L);
        Room mockRoom = new Room();
        mockRoom.setId(1L);

        Booking booking = new Booking(mockRoom, mockUser, LocalDate.now(), LocalDate.now().plusDays(1));

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking savedBooking = bookingService.createBooking(booking);

        assertNotNull(savedBooking);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    public void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBookingById(1L);

        verify(bookingRepository).deleteById(1L);
    }
}
