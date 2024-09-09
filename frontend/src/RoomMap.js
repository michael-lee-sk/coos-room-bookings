import React, { useState } from 'react';

const RoomMap = () => {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');

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

            setRooms(data);
            setLoading(false);
        } catch (error) {
            console.error("Error fetching room availability:", error);
            setError(error.message || 'Unknown error occurred');
            setLoading(false);
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
            <ul>
                {rooms.map(room => (
                    <li key={room.id}>{room.name} - {room.capacity}</li>
                ))}
            </ul>
        </div>
    );
};

export default RoomMap;
