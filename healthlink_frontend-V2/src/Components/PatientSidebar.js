import React from 'react';
import {Link, Route} from 'react-router-dom'; // For navigation to the "Book Appointment" page
import { FaSignOutAlt, FaUser, FaFileMedical, FaCalendarAlt } from 'react-icons/fa'; // Import icons from react-icons
import '../Styles/PatientSidebar.css';
//import AppointmentPage from "../Pages/AppointmentPage"; // Import CSS for the sidebar

const PatientSidebar = ({ setActiveSection }) => {
    const handleLogout = () => {
        // Logic for logging out the user (e.g., clearing tokens, session, etc.)
        console.log("Logged out!");
    };

    return (
        <div className="sidebar-container">
            {/* Main Sidebar Menu */}
            <ul>
                <li onClick={() => setActiveSection('patientInfo')} className="sidebar-item">
                    <FaUser /> Patient Info
                </li>
                <li onClick={() => setActiveSection('medicalRecords')} className="sidebar-item">
                    <FaFileMedical /> Medical Records
                </li>
                <li onClick={() => setActiveSection('appointments')} className="sidebar-item">
                    <FaCalendarAlt /> Appointments
                </li>
            </ul>

            {/* Book Appointment Button */}
            <Link to="/appointments">
                <button className="sidebar-btn-book">
                    <i className="fa fa-calendar-plus"></i> Book Appointment
                </button>
            </Link>

            {/* Logout as a red link */}
            <Link to="#" onClick={handleLogout} className="sidebar-logout-link">
                <FaSignOutAlt /> Logout
            </Link>
        </div>
    );
};

export default PatientSidebar;
