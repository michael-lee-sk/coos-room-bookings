package com.example.coosroombookings.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class GoogleCalendarService {

    private static final String APPLICATION_NAME = "Room Booking Application";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    // Setup the Calendar Service (authentication, etc.)
    private Calendar getCalendarService() throws Exception {
        Credential credential = getCredentials();
        return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private Credential getCredentials() {
        // TODO: Implement OAuth2 flow for authentication
        return null;
    }

    public void createGoogleCalendarEvent(String email, String roomName, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        Calendar calendarService = getCalendarService();

        // Create Event object and set the summary
        Event event = new Event()
                .setSummary("Room Booking: " + roomName)
                .setAttendees(List.of(new EventAttendee().setEmail(email)));

        // Convert LocalDateTime to Date (for Google API)
        Date startDate = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());

        // Set event start and end times
        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startDate))
                .setTimeZone(ZoneId.systemDefault().toString());
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endDate))
                .setTimeZone(ZoneId.systemDefault().toString());
        event.setEnd(end);

        // Insert the event into the user's Google Calendar
        calendarService.events().insert("primary", event).execute();
    }
}
