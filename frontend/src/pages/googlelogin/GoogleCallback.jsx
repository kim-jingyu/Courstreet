import React, { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

const GoogleCallback = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const urlParams = new URLSearchParams(location.search);
        const code = urlParams.get('code');
        console.log(code);

        if (code) {
            axios
                .post('https://your-backend-url/api/auth/google', {
                    authorizationCode: code,
                })
                .then(response => {
                    const accessToken = response.headers.accesstoken;
                    // const { player, nickname } = response.data;

                    localStorage.setItem('accessToken', accessToken);
                    // localStorage.setItem('player', player);
                    // localStorage.setItem('nickname', nickname);
                    navigate('/')
                    // navigate('/room');
                })
                .catch(error => {
                    console.error('Error during the request', error);
                    navigate('/login');
                });
        } else {
            console.error('No code received');
            navigate('/login');
        }
    }, [location, navigate]);

    return <div>Redirecting...</div>;
};

export default GoogleCallback;