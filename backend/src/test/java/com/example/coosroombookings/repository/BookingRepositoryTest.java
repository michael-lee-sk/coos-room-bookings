package com.example.coosroombookings.repository;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void testSaveBooking() {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);
        Booking booking = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        Booking savedBooking = bookingRepository.save(booking);

        assertNotNull(savedBooking.getId());
    }

    @Test
    public void testFindById() {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);
        Booking booking = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        Booking savedBooking = bookingRepository.save(booking);

        Optional<Booking> foundBooking = bookingRepository.findById(savedBooking.getId());

        assertTrue(foundBooking.isPresent());
        assertEquals(savedBooking.getId(), foundBooking.get().getId());
    }

    @Test
    public void testDeleteBooking() {
        Room room = new Room(1L, "Conference Room", 10);
        User user = new User("testuser", "password", "testuser@example.com", true);
        Booking booking = new Booking(room, user, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 2));

        Booking savedBooking = bookingRepository.save(booking);

        bookingRepository.deleteById(savedBooking.getId());

        assertFalse(bookingRepository.findById(savedBooking.getId()).isPresent());
    }
}
