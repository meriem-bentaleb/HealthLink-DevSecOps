import React, { useState } from "react";
import icon2 from "../Assets/icon2.png";
import icon1 from "../Assets/icon1.png";
import icon3 from "../Assets/icon3.png";
import logo from "../Assets/lggrey.png";
import { Link } from "react-router-dom";
import axios from "axios";

function PatientSignup() {
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        phoneNumber: "", // Ajout du champ phoneNumber
        password: "",
        confirmPassword: "",
    });

    const [errors, setErrors] = useState({});
    const [showPassword, setShowPassword] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Initialiser les erreurs de validation
        const validationErrors = {};

        if (!formData.name) validationErrors.name = "Name is required.";
        if (!formData.email) validationErrors.email = "Email is required.";
        if (!formData.phoneNumber) validationErrors.phoneNumber = "Phone number is required."; // Validation pour phoneNumber
        if (!/^\d{10}$/.test(formData.phoneNumber)) validationErrors.phoneNumber = "Phone number must be 10 digits."; // Vérification du format
        if (!formData.password) validationErrors.password = "Password is required.";
        if (formData.password !== formData.confirmPassword) validationErrors.confirmPassword = "Passwords do not match.";

        setErrors(validationErrors);

        // Vérifier s'il n'y a pas d'erreurs avant de soumettre
        if (Object.keys(validationErrors).length === 0) {
            try {
                const response = await axios.post(
                    "http://localhost:2424/auth/register/patient",
                    formData
                );
                console.log("Patient registered successfully:", response.data);
                alert("Patient registered successfully!");
            } catch (error) {
                console.error("Error registering patient:", error);
                // Gérer l'erreur avec un message d'alerte plus spécifique
                alert("Error registering patient: " + (error.response ? error.response.data.message : error.message));
            }
        }
    };

    return (
        <div>
            <section className="bg-[#f5f5f5] text-gray-600 body-font">
                <div className="container px-5 py-24 mx-auto flex flex-wrap items-center">
                    <div className="lg:w-2/6 md:w-1/2 bg-white rounded-lg p-8 flex flex-col md:mr-auto w-full mt-10 md:mt-0">
                        <form onSubmit={handleSubmit}>
                            <div className="relative mb-4">
                                <label htmlFor="name" className="leading-7 text-sm text-gray-600">
                                    Name
                                </label>
                                <input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={formData.name}
                                    onChange={handleChange}
                                    className="w-full bg-[#f5f5f5] rounded-lg border border-gray-300 focus:border-[#008080] focus:ring-2 focus:ring-[#008080] text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                />
                                {errors.name && <p className="text-red-500 text-sm">{errors.name}</p>}
                            </div>

                            <div className="relative mb-4">
                                <label htmlFor="email" className="leading-7 text-sm text-gray-600">
                                    Email
                                </label>
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    className="w-full bg-[#f5f5f5] rounded-lg border border-gray-300 focus:border-[#008080] focus:ring-2 focus:ring-[#008080] text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                />
                                {errors.email && <p className="text-red-500 text-sm">{errors.email}</p>}
                            </div>

                            <div className="relative mb-4">
                                <label htmlFor="phoneNumber" className="leading-7 text-sm text-gray-600">
                                    Phone Number
                                </label>
                                <input
                                    type="text"
                                    id="phoneNumber"
                                    name="phoneNumber"
                                    value={formData.phoneNumber}
                                    onChange={handleChange}
                                    className="w-full bg-[#f5f5f5] rounded-lg border border-gray-300 focus:border-[#008080] focus:ring-2 focus:ring-[#008080] text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                />
                                {errors.phoneNumber && (
                                    <p className="text-red-500 text-sm">{errors.phoneNumber}</p>
                                )}
                            </div>

                            <div className="relative mb-4">
                                <label htmlFor="password" className="leading-7 text-sm text-gray-600">
                                    Password
                                </label>
                                <input
                                    type={showPassword ? "text" : "password"}
                                    id="password"
                                    name="password"
                                    value={formData.password}
                                    onChange={handleChange}
                                    className="w-full bg-[#f5f5f5] rounded-lg border border-gray-300 focus:border-[#008080] focus:ring-2 focus:ring-[#008080] text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                />
                                {errors.password && (
                                    <p className="text-red-500 text-sm">{errors.password}</p>
                                )}
                            </div>

                            <div className="relative mb-4">
                                <label
                                    htmlFor="confirmPassword"
                                    className="leading-7 text-sm text-gray-600"
                                >
                                    Confirm Password
                                </label>
                                <input
                                    type="password"
                                    id="confirmPassword"
                                    name="confirmPassword"
                                    value={formData.confirmPassword}
                                    onChange={handleChange}
                                    className="w-full bg-[#f5f5f5] rounded-lg border border-gray-300 focus:border-[#008080] focus:ring-2 focus:ring-[#008080] text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                />
                                {errors.confirmPassword && (
                                    <p className="text-red-500 text-sm">{errors.confirmPassword}</p>
                                )}
                            </div>

                            <button
                                type="submit"
                                className="text-white bg-[#008080] border-0 py-2 px-8 focus:outline-none hover:bg-[#007272] rounded text-lg"
                            >
                                Register
                            </button>
                        </form>

                        <p className="text-xs text-gray-500 mt-3">
                            Returning user?{" "}
                            <Link to="/Login" className="text-[#008080]">
                                Log in here
                            </Link>
                        </p>
                    </div>

                    <div className="lg:w-2/5 md:w-1/2 md:pl-16 lg:pl-0 pl-0 flex flex-col items-center mx-auto">
                        <img src={logo} alt="HealthLink Logo" className="w-1/2 mb-8" />
                        <ul className="text-lg text-gray-600 leading-relaxed mt-4">
                            <li className="flex mb-2">
                                <img src={icon1} alt="Appointment Icon" className="mr-2 w-7 h-7" />
                                Book Appointments Easily: Schedule appointments with trusted doctors from home.
                            </li>
                            <li className="flex mb-2">
                                <img src={icon2} alt="Appointment Icon" className="mr-2 w-7 h-7" />
                                Access Medical Advice Anytime: Consult doctors via video call and receive timely
                                prescriptions.
                            </li>
                            <li className="flex">
                                <img src={icon3} alt="Appointment Icon" className="mr-2 w-7 h-7" />
                                Stay Informed: Get notifications for upcoming appointments and prescription
                                reminders.
                            </li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    );
}

export default PatientSignup;
