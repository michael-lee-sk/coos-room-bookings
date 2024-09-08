
package com.example.coosroombookings.controller;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
        if (booking == null) {
            return ResponseEntity.badRequest().build();  // Return 400 if booking input is null
        }
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(201).body(savedBooking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = Optional.ofNullable(bookingService.getBookingById(id).orElse(null));
        if (booking.isEmpty()) {
            return ResponseEntity.status(404).build();  // Return 404 if booking not found
        }
        return ResponseEntity.ok(booking.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long id) {
        try {
            bookingService.deleteBookingById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();  // Return 404 if booking not found for deletion
        }
    }
}
