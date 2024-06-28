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
      })
        .then((response) => response.json())
        .then((data) => {
          console.log('Access Token:', data.accessToken);
          // 이후 필요한 처리 로직 추가
          // 예: 로컬 스토리지에 토큰 저장
          localStorage.setItem('accessToken', data.accessToken);
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
