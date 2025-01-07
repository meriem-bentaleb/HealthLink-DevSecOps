import React from 'react';
import '../Styles/ExpertDoctors.css';

const ExpertDoctors = () => {
    return (
        <section id="doctors" className="expert-doctors">
            <p2>Our Expert Doctors</p2>
            <p>Meet our highly qualified and experienced doctors ready to assist you.</p>
            <div className="doctor-cards">
                <div className="doctor-card">
                    <img src="doctor_emily.jpg" alt="Dr. Emily Carter" />
                    <h3>Dr. Emily Carter</h3>
                    <p>Pediatrician</p>
                    <button>Rate</button>
                </div>
                <div className="doctor-card">
                    <img src="doctor_michael.jpg" alt="Dr. Michael Zhang" />
                    <h3>Dr. Michael Zhang</h3>
                    <p>Cardiologist</p>
                    <button>Rate</button>
                </div>
                <div className="doctor-card">
                    <img src="doctor_sarah.jpg" alt="Dr. Sarah Patel" />
                    <h3>Dr. Sarah Patel</h3>
                    <p>Dermatologist</p>
                    <button>Rate</button>
                </div>
            </div>
        </section>
    );
};

export default ExpertDoctors;
