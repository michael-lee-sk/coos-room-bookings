package com.example.coosroombookings.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingModelTest {

    private Booking booking;
    private Room room;
    private User user;

    @BeforeEach
    public void setUp() {
        room = new Room(1L, "Room A", 10);
        user = new User("john", "password", "john@example.com", true);
        booking = new Booking(room, user, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    @Test
    public void testGettersAndSetters() {
        booking.setId(1L);
        assertEquals(1L, booking.getId());
        assertEquals(room, booking.getRoom());
        assertEquals(user, booking.getUser());
        assertNotNull(booking.getStartTime());
        assertNotNull(booking.getEndTime());
    }

    @Test
    public void testEquals_SameObject() {
        Booking booking2 = booking;
        assertEquals(booking, booking2);
    }

    @Test
    public void testEquals_DifferentObjects() {
        Booking booking2 = new Booking(room, user, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        booking2.setId(2L);
        assertNotEquals(booking, booking2);
    }

    @Test
    public void testHashCode() {
        booking.setId(1L);
        Booking booking2 = new Booking(room, user, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        booking2.setId(1L);
        assertEquals(booking.hashCode(), booking2.hashCode());
    }

    @Test
    public void testBooking_WithNullRoomOrUser() {
        assertThrows(NullPointerException.class, () -> new Booking(null, user, LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        assertThrows(NullPointerException.class, () -> new Booking(room, null, LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
    }
}
