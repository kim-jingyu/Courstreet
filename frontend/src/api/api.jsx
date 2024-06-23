import axios from 'axios'

// API 설정
const instance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' },
});

export default instance;