import React, { useEffect, useState } from 'react';

const RoomMap = () => {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const startDate = new Date().toISOString();  // Current date-time
                const endDate = new Date().toISOString();    // Can set future date-time if needed

                const response = await fetch(`/api/rooms/available?startTime=${startDate}&endTime=${endDate}`)
                ;
                
                if (!response.ok) {
                    throw new Error('Failed to fetch room availability');
                }

                const data = await response.json();
                setRooms(data);  // Store the rooms data
            } catch (error) {
                console.error("Error fetching room availability:", error);
                setError(error.message);
            } finally {
                setLoading(false);  // Finish loading
            }
        };

        fetchRooms();  // Call the function when the component loads
    }, []);

    if (loading) {
        return <div>Loading room availability...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (rooms.length === 0) {
        return <div>No rooms available</div>;
    }

    return (
        <div>
            <h1>Church Level 2 Map</h1>
            <div className="room-map">
                {rooms.map((room) => (
                    <div key={room.id} className="room">
                        <h2>Room: {room.name}</h2>
                        <p>Capacity: {room.capacity}</p>
                        <p>Status: {room.isAvailable ? "Available" : "Unavailable"}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default RoomMap;
