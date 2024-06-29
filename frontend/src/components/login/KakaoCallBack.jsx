import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const KakaoLoginCallback = () => {
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const code = params.get('code');

    if (code) {
      fetch('http://localhost:8080/login/kakao', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ code }),
        credentials: 'include', // 쿠키 포함
      })
        .then((response) => response.json())
        .then((data) => {
          const accessToken = data.accessToken; 
          console.log('Access Token:', accessToken);
          // 로컬 스토리지에 토큰 저장
          localStorage.setItem('accessToken', accessToken);
          // memberId 로컬 스토리지에 저장
          const tokenParts = accessToken.split('.');
          const encodedPayload = tokenParts[1];
          const decodedPayload = atob(encodedPayload.replace(/-/g, '+').replace(/_/g, '/'));
          const payloadObj = JSON.parse(decodedPayload);
          const memberId = payloadObj.sub; 
          console.log('Member ID', memberId);
          localStorage.setItem('memberId', memberId);
          navigate('/'); // 메인 페이지로 리디렉션
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    }
  }, [location, navigate]);

  return <div>Kakao 로그인 중...</div>;
};

export default KakaoLoginCallback;
