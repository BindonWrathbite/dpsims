import axios from 'axios';

const api = axios.create({
  baseURL: 'https://localhost:8000/api', // Base URL for the API
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true, // Send cookies with requests
});

export default api;