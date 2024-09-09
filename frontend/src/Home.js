import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div>
      <h1>Welcome to the Home Page!</h1>
      <p>You are successfully logged in.</p>
      
      {/* Navigation buttons */}
      <div>
        <Link to="/map">
          <button>View Room Map</button>
        </Link>
        {/* Add more buttons for navigation as needed */}
      </div>
    </div>
  );
}

export default Home;
