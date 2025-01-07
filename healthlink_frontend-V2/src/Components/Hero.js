import React from 'react';
import '../Styles/Hero.css';
import {Link} from "react-router-dom"; // Import the corresponding CSS file

const Hero = () => {
    return (
        <section className="hero text-center">
            <h1>Simplifying Your Path to Better Health</h1>
            <p>Your Journey to Better Health Starts Here</p>
            <Link to="/DocSignup" className="btn btn-primary mx-2">
                Get Started as a Doctor
            </Link>
            <Link to="/PatientSignup" className="btn btn-primary mx-2">
                Get Started as a Patient
            </Link>
            <h2>
                Already have an account?{" "}
                <Link to="/Login" className="login-link">Log in here</Link>
            </h2>
        </section>
    );
}

export default Hero;
