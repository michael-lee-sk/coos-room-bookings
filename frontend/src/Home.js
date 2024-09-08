import React, { useState, useEffect } from 'react';

const Home = () => {
  const [rooms, setRooms] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchRooms = async () => {
      const token = localStorage.getItem('token');

      try {
        const response = await fetch('http://localhost:8080/rooms', {
          headers: {
            'Authorization': `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setRooms(data);
        } else {
          setError('Failed to fetch rooms');
        }
      } catch (error) {
        setError('Failed to connect to server');
      }
    };

    fetchRooms();
  }, []);

  return (
    <div>
      <h2>Available Rooms</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <ul>
        {rooms.map((room) => (
          <li key={room.id}>{room.name} - {room.capacity} people</li>
        ))}
      </ul>
    </div>
  );
};

export default Home;
