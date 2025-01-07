import '../Styles/Login.css';
import React, { useState } from 'react';
import logo from '../Assets/logohori.png';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const API_URL = 'http://localhost:2424/auth'; // URL de ton back-end

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    // Fonction pour appeler l'API Spring Boot
    const login = async (email, password) => {
        try {
            const response = await axios.post(`${API_URL}/login`, { email, password });
            return response.data; // Retourne les données reçues (token, role, etc.)
        } catch (error) {
            throw error.response ? error.response.data.message : 'Login failed';
        }
    };

    // Gestion du formulaire de connexion
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const userData = await login(email, password);
            console.log('User data:', userData);

            if (userData.token) {
                // Enregistre le token et le rôle dans localStorage
                localStorage.setItem('token', userData.token);
                localStorage.setItem('role', userData.role);

                // Redirection selon le rôle de l'utilisateur
                if (userData.role === 'PATIENT') {
                    navigate('/profile'); // Redirige vers la page du patient
                } else if (userData.role === 'DOCTOR') {
                    navigate('/'); // Redirige vers la page du médecin
                }
            }
        } catch (error) {
            console.error('Login error:', error);
            setError(error || 'Login failed. Please try again.');
            setTimeout(() => setError(''), 5000); // Réinitialise l'erreur après 5 secondes
        }
    };

    return (
        <div className="login-page">
            <div className="login-header">
                <img src={logo} alt="HealthLink Logo" className="logo" />
                <Link to="/DocSignup">
                    <button className="signup-btn">Sign Up</button>
                </Link>
            </div>
            <div className="login-container">
                <h2>Log in to Your HealthLink Account</h2>
                {error && <p className="error-message">{error}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="textbox" id="email-textbox">
                        <label className="label">Email</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="Enter your email"
                            required
                        />
                    </div>
                    <div className="textbox" id="password-textbox">
                        <label className="label">Password</label>
                        <div className="password-container">
                            <input
                                type={showPassword ? 'text' : 'password'}
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder="Enter your password"
                                required
                            />
                            <span className="icon" onClick={() => setShowPassword(!showPassword)}>
                                {showPassword ? '👁️' : '👁️‍🗨️'}
                            </span>
                        </div>
                    </div>
                    <button type="submit" className="login-btn">Log In</button>
                    <div className="forgot-password">
                        <a href="#">Forgot Password?</a>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Login;
