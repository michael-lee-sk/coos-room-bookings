
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.coosroombookings.controller.RoomController;
import com.example.coosroombookings.model.Room;
import com.example.coosroombookings.service.RoomService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRoom() {
        Room mockRoom = new Room();
        mockRoom.setId(1L);
        mockRoom.setName("Room A");
        mockRoom.setCapacity(10);

        // Simulate room creation by the service
        when(roomService.createRoom(any(Room.class))).thenReturn(mockRoom);

        // Test the controller method
        ResponseEntity<Room> response = roomController.addRoom(mockRoom);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockRoom.getName(), response.getBody().getName());
        verify(roomService).createRoom(any(Room.class));
    }

    @Test
    public void testAddRoom_NullInput() {
        // Test with null input to simulate a bad request scenario
        ResponseEntity<Room> response = roomController.addRoom(null);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testGetAvailableRooms() {
        List<Room> mockRooms = Arrays.asList(
                new Room(1L, "Room A", 5),
                new Room(2L, "Room B", 10)
        );

        LocalDateTime startDate = LocalDateTime.of(2023, 9, 10, 14, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 12, 12, 0);

        // Simulate the service response with params
        when(roomService.getAvailableRooms(startDate, endDate)).thenReturn(mockRooms);

        // Call the controller method
        List<Room> availableRooms = roomController.getAvailableRooms(startDate, endDate);

        assertEquals(2, availableRooms.size());
        verify(roomService).getAvailableRooms(startDate, endDate);
    }

    @Test
    public void testGetAvailableRooms_EmptyList() {
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 10, 14, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 12, 12, 0);

        // Simulate an empty result
        when(roomService.getAvailableRooms(startDate, endDate)).thenReturn(Arrays.asList());

        // Call the controller method
        List<Room> availableRooms = roomController.getAvailableRooms(startDate, endDate);

        assertEquals(0, availableRooms.size());
        verify(roomService).getAvailableRooms(startDate, endDate);
    }

    @Test
    public void testGetRoomById() {
        Room mockRoom = new Room();
        mockRoom.setId(1L);
        mockRoom.setName("Room A");

        // Simulate the service behavior
        when(roomService.findRoomById(1L)).thenReturn(mockRoom);

        // Call the controller method
        ResponseEntity<Room> response = roomController.getRoomById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockRoom.getName(), response.getBody().getName());
        verify(roomService).findRoomById(1L);
    }

    @Test
    public void testGetRoomById_NotFound() {
        // Simulate the service returning null for a non-existent room
        when(roomService.findRoomById(999L)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Room> response = roomController.getRoomById(999L);

        assertEquals(404, response.getStatusCodeValue());
        verify(roomService).findRoomById(999L);
    }

    @Test
    public void testUpdateRoom() {
        Room mockRoom = new Room();
        mockRoom.setId(1L);
        mockRoom.setName("Updated Room");
        mockRoom.setCapacity(15);

        // Simulate service update behavior
        when(roomService.updateRoom(eq(1L), any(Room.class))).thenReturn(mockRoom);

        // Call the controller method
        ResponseEntity<Room> response = roomController.updateRoom(1L, mockRoom);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Room", response.getBody().getName());
        assertEquals(15, response.getBody().getCapacity());
        verify(roomService).updateRoom(eq(1L), any(Room.class));
    }

    @Test
    public void testUpdateRoom_NotFound() {
        Room mockRoom = new Room();
        mockRoom.setId(999L);
        mockRoom.setName("Non-Existent Room");

        // Simulate service returning null for a non-existent room
        when(roomService.updateRoom(eq(999L), any(Room.class))).thenReturn(null);

        // Call the controller method
        ResponseEntity<Room> response = roomController.updateRoom(999L, mockRoom);

        assertEquals(404, response.getStatusCodeValue());
        verify(roomService).updateRoom(eq(999L), any(Room.class));
    }

    @Test
    public void testDeleteRoom() {
        // Simulate service behavior for deletion
        doNothing().when(roomService).deleteRoomById(1L);

        // Call the controller method
        ResponseEntity<Void> response = roomController.deleteRoom(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(roomService).deleteRoomById(1L);
    }

    @Test
    public void testDeleteRoom_NotFound() {
        // Simulate service throwing an exception for non-existent room
        doThrow(new RuntimeException("Room not found")).when(roomService).deleteRoomById(999L);

        // Call the controller method and expect an exception
        ResponseEntity<Void> response = roomController.deleteRoom(999L);

        assertEquals(404, response.getStatusCodeValue());
        verify(roomService).deleteRoomById(999L);
    }

    @Test
    public void testSearchRooms() {
        List<Room> mockRooms = Arrays.asList(
                new Room(1L, "Room A", 5),
                new Room(2L, "Room B", 10)
        );

        // Simulate service search behavior
        when(roomService.searchRooms("Room", 5)).thenReturn(mockRooms);

        // Call the controller method
        ResponseEntity<List<Room>> response = roomController.searchRooms("Room", 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(roomService).searchRooms("Room", 5);
    }
}
