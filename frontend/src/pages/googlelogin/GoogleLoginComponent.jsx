import React from 'react';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { useNavigate } from 'react-router-dom';
import { handleGoogleAuth } from '/src/api/login';


const GoogleLoginComponent =   () => {
  const navigate = useNavigate();

  const onSuccess = (response) => {
    const code = response.code;
    handleGoogleAuth(code, navigate);
  };

  const onFailure = (error) => {
    console.error('Google Login Error:', error);
    navigate('/error');
  };

  return (
    <GoogleOAuthProvider clientId="YOUR_GOOGLE_CLIENT_ID">
      <div>
        <h2>Login with Google</h2>
        <GoogleLogin
          onSuccess={onSuccess}
          onFailure={onFailure}
          useOneTap
        />
      </div>
    </GoogleOAuthProvider>
  );
};

export default GoogleLoginComponent;
