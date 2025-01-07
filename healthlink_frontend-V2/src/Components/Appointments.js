import React from 'react';
import '../Styles/Appointments.css';

const Appointments = ({ appointments }) => {
    return (
        <div className="appointments">
            <h2>Your Appointments</h2>
            <ul>
                {appointments.map((appointment) => (
                    <li key={appointment.id}>
                        <p><strong>Date: </strong>{appointment.date}</p>
                        <p><strong>Doctor: </strong>{appointment.doctorName}</p>
                        <p><strong>Status: </strong>{appointment.status}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Appointments;
