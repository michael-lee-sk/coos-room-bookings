import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './RoomMap.css';

function RoomMap() {
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    // Fetch room data from the backend (no date params needed)
    fetch('http://localhost:8080/api/rooms/available')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Fetched Rooms Data:', data); // Log the data to check if it's fetched
        setRooms(data); // Update the state with the fetched rooms data
      })
      .catch(error => console.error('Error fetching room availability:', error));
  }, []);

  return (
    <div className="map-container">
      <h1>Church Level 2 Map</h1>
      <div className="map">
        {rooms.length > 0 ? (
          rooms.map((room) => (
            <div
              key={room.id}
              className={`room ${room.available ? 'available' : 'unavailable'}`}
            >
              <Link to={`/room/${room.id}`}>
                {room.name} - {room.available ? 'Available' : 'Booked'}
              </Link>
            </div>
          ))
        ) : (
          <p>No rooms available</p>
        )}
      </div>
    </div>
  );
}

export default RoomMap;
