package com.example.coosroombookings.handler;

import com.example.coosroombookings.controller.BookingController;
import com.example.coosroombookings.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    public void testRuntimeExceptionHandled() throws Exception {
        Mockito.when(bookingService.getBookingById(1L)).thenThrow(new RuntimeException("Generic Error"));

        mockMvc.perform(get("/api/bookings/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Generic Error"));
    }
}
