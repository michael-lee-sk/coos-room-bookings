package com.example.coosroombookings.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingModelTest {

    @Test
    public void testBookingCreation() {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);
        Booking booking = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        assertNotNull(booking);
        assertEquals(room, booking.getRoom());
        assertEquals(user, booking.getUser());
        assertEquals(LocalDate.of(2024, 9, 1), booking.getStartDate());
        assertEquals(LocalDate.of(2024, 9, 2), booking.getEndDate());
    }

    @Test
    public void testBookingEqualsAndHashCode() {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);

        Booking booking1 = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));
        Booking booking2 = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        assertEquals(booking1, booking2);
        assertEquals(booking1.hashCode(), booking2.hashCode());
    }
}
