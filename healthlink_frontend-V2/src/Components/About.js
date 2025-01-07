import React from 'react';
import '../Styles/About.css';

const About = () => {
    return (
        <section id="about" className="about-section">
            <l>About HealthLink</l>
            <p>
                HealthLink is your one-stop solution for seamless healthcare access. We connect patients and doctors
                through cutting-edge telemedicine technology, enabling faster consultations, easier appointment
                booking, and secure management of medical records.
            </p>
            <div className="about-features">
                <div className="about-feature">
                    <h3>Convenience</h3>
                    <p>Access healthcare services from the comfort of your home, anytime and anywhere.</p>
                </div>
                <div className="about-feature">
                    <h3>Trusted Professionals</h3>
                    <p>Connect with certified and experienced doctors for reliable medical advice.</p>
                </div>
                <div className="about-feature">
                    <h3>Secure and Confidential</h3>
                    <p>Your medical information is stored securely and shared only with your consent.</p>
                </div>
            </div>
        </section>
    );
};

export default About;
