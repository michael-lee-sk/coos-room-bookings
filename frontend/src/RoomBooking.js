import React from 'react';
import { useParams } from 'react-router-dom';

function RoomBooking() {
  const { id } = useParams();
  
  const room = rooms.find(room => room.id === parseInt(id));  // Dummy room lookup

  return (
    <div>
      <h1>Booking Room {room.name}</h1>
      {room.available ? (
        <div>
          <p>This room is available for booking.</p>
          {/* Booking form will go here */}
          <button>Confirm Booking</button>
        </div>
      ) : (
        <p>This room is already booked.</p>
      )}
    </div>
  );
}

export default RoomBooking;
