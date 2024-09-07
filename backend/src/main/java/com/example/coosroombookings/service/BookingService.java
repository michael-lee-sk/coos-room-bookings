package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        // Make sure the request-provided dates are used and not overwritten by LocalDate.now()
        return bookingRepository.save(booking);
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBookingById(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
