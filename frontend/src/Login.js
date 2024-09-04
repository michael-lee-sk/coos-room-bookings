import React from 'react';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';

const Login = () => {
  const handleLoginSuccess = (credentialResponse) => {
    console.log('Login Success:', credentialResponse);
    window.location.href = '/api/oauth2/google'; // Adjust this to match your backend URL
  };

  const handleLoginFailure = () => {
    console.error('Login Failed');
  };

  return (
    <GoogleOAuthProvider clientId="594287888695-kq3r0hb8emd2q44pcj6deb76lm8f6de0.apps.googleusercontent.com">
      <div className="login-container">
        <h2>Login with Google</h2>
        <GoogleLogin
          onSuccess={handleLoginSuccess}
          onError={handleLoginFailure}
        />
      </div>
    </GoogleOAuthProvider>
  );
};

export default Login;