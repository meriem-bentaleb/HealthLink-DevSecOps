import React from 'react';
import '../Styles/Services.css';

const Services = () => {
    return (
        <section id="services" className="services">
            <m>Discover Our Services</m>
            <p>Explore various healthcare services we offer to enhance your well-being.</p>
            <div className="service-cards">
                <div className="service-card">
                    <img src="telemedicine.jpg" alt="Telemedicine" />
                    <h3>Telemedicine</h3>
                    <p>Consult with certified doctors remotely via video call.</p>
                    <button>Start</button>
                </div>
                <div className="service-card">
                    <img src="prescription.jpg" alt="Prescription Management" />
                    <h3>Prescription Management</h3>
                    <p>Receive and manage prescriptions from your doctor with automated refill reminders.</p>
                    <button>Start</button>
                </div>
                <div className="service-card">
                    <img src="firstAid.jpg" alt="First Aid Tips" />
                    <h3>First Aid Tips</h3>
                    <p>Learn essential first aid tips for emergencies.</p>
                    <button>Start</button>
                </div>
                <div className="service-card">
                    <img src="medical_record.jpg" alt="Medical Record Access" />
                    <h3>Medical Record Access</h3>
                    <p>View and share your medical history securely with doctors for better care coordination.</p>
                    <button>Start</button>
                </div>
            </div>
        </section>
    );
};

export default Services;
