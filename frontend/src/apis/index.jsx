// 토큰 관련 API들
// 작성자: 김진규

import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
// axios 요청에 기본적으로 쿠키, 헤더 등을 포함하도록 설정
axios.defaults.withCredentials = true;

// 토큰 불필요한 요청
export const publicApi = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// 토큰 필요한 요청
export const privateApi = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// privateApi 요청 헤더에 항상 accessToken을 삽입
privateApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('accessToken');
  if (token) {
    config.headers.Authorization = 'Bearer ' + token;
  }
  return config;
});

// privateApi 응답 시 에러가 발생하면 refreshToken 재발급
privateApi.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    if (error.response && error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      try {
        const response = await postRefreshToken();
        console.log('Token refresh response:', response.data);
        const newAccessToken = response.data.accessToken;
        localStorage.setItem('accessToken', newAccessToken);
        axios.defaults.headers.common['Authorization'] = `Bearer ${newAccessToken}`;
        originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
        return axios(originalRequest);
      } catch (refreshError) {
        console.error('Token refresh failed:', refreshError);
        return Promise.reject(refreshError);
      }
    }
    return Promise.reject(error);
  },
);

// refreshToken 재발급 요청
export async function postRefreshToken() {
  const token = localStorage.getItem('accessToken'); // 기존 accessToken 가져오기
  try {
    const response = await publicApi.post(
      '/token',
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    console.log('postRefreshToken response:', response);
    return response;
  } catch (error) {
    console.error('postRefreshToken error:', error);
    throw error;
  }
}
