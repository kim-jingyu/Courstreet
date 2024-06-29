import axios from 'axios';
import Cookies from 'js-cookie';

const BASE_URL = 'http://localhost:8080';
axios.defaults.withCredentials = true;

export const publicApi = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const privateApi = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
  },
});

privateApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('accessToken');
  config.headers.Authorization = 'Bearer ' + token;
  return config;
});

export async function postRefreshToken() {
  const refreshToken = Cookies.get('refreshToken');
  if (refreshToken) {
    const info = {
      refreshToken: refreshToken,
    };
    return await publicApi.post('/token', info);
  } else {
    // 쿠키에서 refreshToken을 가져올 수 없는 경우 처리
    throw new Error('Refresh token not found');
  }
}

privateApi.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const { config } = error;
    const originRequest = config;
    if (error.message === 'Network Error') {
      try {
        const response = await postRefreshToken();
        const newAccessToken = response.data.accessToken;
        const newRefreshToken = response.data.refreshToken;

        localStorage.setItem('accessToken', newAccessToken);
        Cookies.set('refreshToken', newRefreshToken, { secure: true, sameSite: 'Lax' });

        axios.defaults.headers.common['Authorization'] = `Bearer ${newAccessToken}`;
        originRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;

        return axios(originRequest);
      } catch {
        localStorage.removeItem('accessToken');
        Cookies.remove('refreshToken');
        window.location.href = '/';
      }
    }
    return Promise.reject(error);
  },
);
