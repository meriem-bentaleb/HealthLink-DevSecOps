// /src/api/api.js
import axios from 'axios';

// Create an Axios instance
const api = axios.create({
    baseURL: 'http://localhost:8080/api',  // Backend API base URL (Spring Boot server)
    headers: {
        'Content-Type': 'application/json',
    },
});

export default api;
