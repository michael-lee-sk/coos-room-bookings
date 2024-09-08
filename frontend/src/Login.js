import React from 'react';
import './Login.css';

const Login = () => {
  const handleOAuthLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };

  return (
    <div className="login-container">
      <h2>Login with Google</h2>
      <button onClick={handleOAuthLogin}>Login with Google</button>
    </div>
  );
};

export default Login;
