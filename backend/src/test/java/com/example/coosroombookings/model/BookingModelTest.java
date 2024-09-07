package com.example.coosroombookings.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingModelTest {

    @Test
    public void testBookingCreation() {
        Booking booking = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        assertNotNull(booking);
        assertEquals(1L, booking.getId());
        assertEquals(1L, booking.getRoomId());
        assertEquals(LocalDate.of(2024, 9, 1), booking.getStartDate());
        assertEquals(LocalDate.of(2024, 9, 2), booking.getEndDate());
    }

    @Test
    public void testBookingEqualsAndHashCode() {
        Booking booking1 = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));
        Booking booking2 = new Booking(1L, 1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        assertEquals(booking1, booking2);  // Should now pass with custom equals method
        assertEquals(booking1.hashCode(), booking2.hashCode());  // Should now pass with custom hashCode method
    }
}
