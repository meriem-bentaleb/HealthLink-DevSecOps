import React from 'react';
import '../Styles/Appointment.css';
import {Link} from "react-router-dom";

const Appointment = () => {
    return (
        <section id="appointment" className="appointment">
            <p3>Make An Appointment</p3>
            <p>
                Easily schedule your health consultations at your convenience. Choose your desired
                experience and specialty, then select a suitable date and time.
            </p>
            <Link to="/appointments" className="btn btn-primary mx-2">
                Book Now
            </Link>
        </section>
    );
};

export default Appointment;
