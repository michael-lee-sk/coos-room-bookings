package com.example.coosroombookings.repository;

import com.example.coosroombookings.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Use the full constructor to create a test user
        testUser = new User("testUser", "testPass", "test@example.com", true);
    }

    @Test
    public void testSaveUser() {
        // Save the user to the repository
        User savedUser = userRepository.save(testUser);

        // Assert that the user was saved and the details match
        assertNotNull(savedUser.getId());
        assertEquals("testUser", savedUser.getUsername());
        assertEquals("testPass", savedUser.getPassword());
        assertEquals("test@example.com", savedUser.getEmail());
        assertTrue(savedUser.isEnabled());
    }

    @Test
    public void testFindUserById() {
        // Save the user and retrieve it by ID
        User savedUser = userRepository.save(testUser);
        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);

        // Assert that the user was found and matches the saved details
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("testPass", foundUser.getPassword());
        assertEquals("test@example.com", foundUser.getEmail());
        assertTrue(foundUser.isEnabled());
    }

    @Test
    public void testDeleteUser() {
        // Save the user, then delete it
        User savedUser = userRepository.save(testUser);
        userRepository.deleteById(savedUser.getId());

        // Assert that the user is no longer in the repository
        User deletedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNull(deletedUser);
    }
}
