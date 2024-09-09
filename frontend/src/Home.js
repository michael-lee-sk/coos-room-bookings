import React, { useEffect, useState } from 'react';

const Home = () => {
  const [message, setMessage] = useState('');

  useEffect(() => {
    // Fetch message from backend
    fetch('http://localhost:8080/home')
      .then(response => response.text())
      .then(data => setMessage(data))
      .catch(error => console.error('Error fetching home message:', error));
  }, []);

  return (
    <div>
      <h1>{message}</h1>
    </div>
  );
};

export default Home;
