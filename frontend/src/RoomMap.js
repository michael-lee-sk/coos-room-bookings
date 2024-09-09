import React, { useState } from 'react';

const RoomMap = () => {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const [nextAvailableRoom, setNextAvailableRoom] = useState(null);

    const fetchRooms = async () => {
        if (!startTime || !endTime) {
            return;
        }
        try {
            setLoading(true);
            console.log(`Requesting available rooms from ${startTime} to ${endTime}`);

            const response = await fetch(
                `http://localhost:8080/api/rooms/available?startTime=${encodeURIComponent(startTime)}&endTime=${encodeURIComponent(endTime)}`,
                {
                    method: "GET",
                    credentials: "include",
                    headers: {
                        "Content-Type": "application/json"
                    }
                }
            );

            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }

            const data = await response.json();
            console.log("Received data:", data);

            if (data.length === 0) {
                // No rooms available, find the next available room
                await findNextAvailableRoom();
            } else {
                setRooms(data);
                setNextAvailableRoom(null); // Reset next available room
            }
            setLoading(false);
        } catch (error) {
            console.error("Error fetching room availability:", error);
            setError(error.message || 'Unknown error occurred');
            setLoading(false);
        }
    };

    const findNextAvailableRoom = async () => {
        try {
            const response = await fetch(
                `http://localhost:8080/api/rooms/next-available?startTime=${encodeURIComponent(startTime)}&endTime=${encodeURIComponent(endTime)}`,
                {
                    method: "GET",
                    credentials: "include",
                    headers: {
                        "Content-Type": "application/json"
                    }
                }
            );

            if (response.status === 204) {
                setNextAvailableRoom(null); // No room found
                return;
            }

            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }

            const data = await response.json();
            console.log("Next available room data:", data);
            if (data) {
                setNextAvailableRoom(data);
            } else {
                setNextAvailableRoom(null); // No room found
            }
        } catch (error) {
            console.error("Error fetching next available room:", error);
            setNextAvailableRoom(null);
        }
    };

    const handleSearch = (e) => {
        e.preventDefault();
        fetchRooms();
    };

    return (
        <div>
            <h1>Available Rooms</h1>
            <form onSubmit={handleSearch}>
                <label>
                    Start Time:
                    <input
                        type="datetime-local"
                        value={startTime}
                        onChange={(e) => setStartTime(e.target.value)}
                    />
                </label>
                <label>
                    End Time:
                    <input
                        type="datetime-local"
                        value={endTime}
                        onChange={(e) => setEndTime(e.target.value)}
                    />
                </label>
                <button type="submit">Search</button>
            </form>
            {loading && <div>Loading...</div>}
            {error && <div>Error: {error}</div>}
            {rooms.length === 0 && !loading && !error && <div>No rooms available for the selected time.</div>}
            {nextAvailableRoom && <div>Next available room: {nextAvailableRoom.name} - {nextAvailableRoom.capacity}</div>}
            <ul>
                {rooms.map(room => (
                    <li key={room.id}>{room.name} - {room.capacity}</li>
                ))}
            </ul>
        </div>
    );
};

export default RoomMap;
