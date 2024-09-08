package com.example.coosroombookings.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);  // New method to find booking by ID
    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }
}
