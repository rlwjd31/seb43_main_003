import axios from 'axios';

const BASE_URL = import.meta.env.VITE_BASE_URL;

const config = {
  baseURL: BASE_URL,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json',
    'ngrok-skip-browser-warning': '69420',
  },
};

export default axios.create(config);
