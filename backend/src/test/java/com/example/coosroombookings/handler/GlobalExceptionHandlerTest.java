package com.example.coosroombookings.handler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import com.example.coosroombookings.repository.UserRepository;
import com.example.coosroombookings.repository.BookingRepository;
import com.example.coosroombookings.repository.RoomRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Disable JPA and DataSource auto-configuration to avoid loading unnecessary repositories or databases
@WebMvcTest(controllers = TestController.class)
@Import(GlobalExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)  // Disable Spring Security filters for this test
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock the repositories to prevent JPA initialization
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    public void testGlobalExceptionHandler() throws Exception {
        mockMvc.perform(get("/test/exception"))
                .andExpect(status().isInternalServerError())  // Expecting 500 for RuntimeException
                .andReturn();
    }
}

@RestController
class TestController {

    @GetMapping("/test/exception")
    public String triggerException() {
        throw new RuntimeException("Test Exception");
    }
}
