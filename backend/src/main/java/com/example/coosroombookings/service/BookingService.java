package com.example.coosroombookings.service;

import com.example.coosroombookings.model.Booking;
import com.example.coosroombookings.model.BookingRequest;
import com.example.coosroombookings.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GoogleCalendarService googleCalendarService;

    public boolean bookRoom(BookingRequest request) {
        try {
            // Convert BookingRequest to Booking entity
            Booking booking = new Booking();
            booking.setRoomName(request.getRoomName());
            booking.setStartTime(request.getStartTime());
            booking.setEndTime(request.getEndTime());
            booking.setEmail(request.getEmail());

            // Save the Booking entity to the database
            bookingRepository.save(booking);

            // Create Google Calendar event after successful booking
            googleCalendarService.createGoogleCalendarEvent(
                    request.getEmail(),
                    request.getRoomName(),
                    request.getStartTime(),
                    request.getEndTime()
            );

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
