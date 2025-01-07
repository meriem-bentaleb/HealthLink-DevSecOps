import React, { useState } from "react";
import "../Styles/DocSignup.css";
import logoImage from "../Assets/logovert.png";
import minlog from "../Assets/minilogo.png";
import { Link } from 'react-router-dom';
import axios from "axios";

const DocSignup = () => {
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
        city: "",
        country: "",
        specialty: "",
        address: "",
        phoneNumber: "",
        officePhoneNumber: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:2424/auth/register/doctor', formData);
            console.log("Doctor registered successfully:", response.data);
            alert("Doctor registered successfully!");
        } catch (error) {
            if (error.response) {
                // Erreur renvoyée par le serveur
                console.error("Error response data:", error.response.data);
                console.error("Error response status:", error.response.status);
                console.error("Error response headers:", error.response.headers);

                alert(`Error registering doctor: ${error.response.data.message || "An unknown error occurred"}`);
            } else if (error.request) {
                // La requête a été envoyée mais aucune réponse n'a été reçue
                console.error("Error request:", error.request);
                alert("No response from the server. Please try again later.");
            } else {
                // Erreur lors de la configuration de la requête
                console.error("Error message:", error.message);
                alert(`An error occurred: ${error.message}`);
            }
        }
    };



    return (
        <div className="register-doc-container">
            <div className="header-with-logo">
                <img src={minlog} alt="Logo" className="mini-logon"/>
            </div>
            <div className="form-and-description-container">
                <form onSubmit={handleSubmit} className="register-doc-form">

                    <h2>Create Your Professional HealthLink Account</h2>
                    <div className="form-columns">
                        {/* Colonne gauche */}
                        <div className="form-left">
                            <div className="form-group">
                                <label>Name</label>
                                <input
                                    type="text"
                                    name="name"
                                    value={formData.name}
                                    onChange={handleChange}
                                    placeholder="Input name"
                                />
                            </div>


                            <div className="form-group">
                                <label>Password</label>
                                <input
                                    type="password"
                                    name="password"
                                    value={formData.password}
                                    onChange={handleChange}
                                    placeholder="Enter at least 8+ characters"
                                />
                            </div>

                            <div className="form-group">
                                <label>City</label>
                                <input
                                    type="text"
                                    name="city"
                                    value={formData.city}
                                    onChange={handleChange}
                                    placeholder="Enter your city"
                                />
                            </div>

                            <div className="form-group">
                                <label>Specialty</label>
                                <input
                                    type="text"
                                    name="specialty"
                                    value={formData.specialty}
                                    onChange={handleChange}
                                    placeholder="Choose your specialty"
                                />
                            </div>

                            <div className="form-group">
                                <label>Phone Number</label>
                                <input
                                    type="tel"
                                    name="phoneNumber"
                                    value={formData.phoneNumber}
                                    onChange={handleChange}
                                    placeholder="0661-xxxxxxx"
                                />
                            </div>
                        </div>

                        {/* Colonne droite du formulaire */}
                        <div className="form-right">
                            <div className="form-group">
                                <label>Email</label>
                                <input
                                    type="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    placeholder="example.email@gmail.com"
                                />
                            </div>

                            <div className="form-group">
                                <label>Confirm Password</label>
                                <input
                                    type="password"
                                    name="confirmPassword"
                                    value={formData.confirmPassword}
                                    onChange={handleChange}
                                    placeholder="Confirm your password"
                                />
                            </div>

                            <div className="form-group">
                                <label>Country</label>
                                <input
                                    type="text"
                                    name="country"
                                    value={formData.country}
                                    onChange={handleChange}
                                    placeholder="Enter your country"
                                />
                            </div>

                            <div className="form-group">
                                <label>Office Address</label>
                                <input
                                    type="text"
                                    name="address"
                                    value={formData.address}
                                    onChange={handleChange}
                                    placeholder="Enter office address"
                                />
                            </div>

                            <div className="form-group">
                                <label>Office Phone Number</label>
                                <input
                                    type="tel"
                                    name="officePhoneNumber"
                                    value={formData.officePhoneNumber}
                                    onChange={handleChange}
                                    placeholder="0661-xxxxxxx"
                                />
                            </div>
                        </div>
                    </div>

                    <div className="form-group-checkbox">
                        <label>
                            <input type="checkbox" required/> I certify on my honor that I am a health professional.
                        </label>
                    </div>

                    <button type="submit" className="register-btn">Register</button>
                    <p>Returning user? <Link to="/Login"><a href="#">Log in here</a></Link></p>
                </form>

                {/* Troisième colonne pour la description */}
                <div className="register-doc-right">
                    <img src={logoImage} alt="HealthLink Logo" className="logo1"/>

                    <div className="steps">
                        <div className="step">
                            <span className="step-number">1</span>
                            <p>Create your HealthLink profile</p>
                        </div>
                        <div className="step">
                            <span className="step-number">2</span>
                            <p>
                                A member of our team will contact you to confirm your account.
                            </p>
                        </div>
                    </div>

                    <div className="extra-features">
                        <p>Grow Your Practice: Connect with patients easily and manage appointments online.</p>
                        <p>Offer Remote Care: Provide consultations through secure video calls.</p>
                        <p>Access Patient Medical Records: Review and update treatment plans in real time.</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DocSignup;