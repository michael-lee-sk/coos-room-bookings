import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Login from './Login'; // Ensure the correct path to Login.js

function App() {
  return (
    <Router>
      <div className="App">
        <h1>Welcome to COOS Room Bookings</h1>
        {/* Add a link to navigate to the login page */}
        <Link to="/login">
          <button>Login</button>
        </Link>
        
        <Routes>
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;