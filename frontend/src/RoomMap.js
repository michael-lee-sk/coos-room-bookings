import React, { useEffect, useState } from 'react';

const RoomMap = () => {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
      const fetchRooms = async () => {
        try {
            const startTime = new Date().toISOString();
            const endTime = new Date(new Date().getTime() + 60 * 60 * 1000).toISOString();
            
            console.log(`Requesting available rooms from ${startTime} to ${endTime}`);

            const response = await fetch("http://localhost:8080/api/rooms/available", {
              method: "POST",  // Use POST instead of GET
              credentials: "include",
              headers: {
                  "Content-Type": "application/json"
              },
              body: JSON.stringify({ startTime, endTime })  // Send startTime and endTime in the body
          });
          

            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }

            const data = await response.json();  // Parse the JSON response
            console.log("Received data:", data);

            setRooms(data);  // Save the room data
            setLoading(false);  // Stop loading
        } catch (error) {
            console.error("Error fetching room availability:", error);
            setError(error.message || 'Unknown error occurred');
            setLoading(false);
        }
    };

    fetchRooms();
    }, []);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h1>Available Rooms</h1>
            <ul>
                {rooms.map(room => (
                    <li key={room.id}>{room.name} - {room.capacity}</li>
                ))}
            </ul>
        </div>
    );
};

export default RoomMap;