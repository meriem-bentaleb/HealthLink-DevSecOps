import React from 'react';
import '../Styles/Reviews.css';

const Reviews = () => {
    return (
        <section id="reviews" className="reviews-section">
            <p1>What Our Users Say</p1>
            <p>See how HealthLink has transformed the healthcare experience for our users.</p>
            <div className="review-cards">
                <div className="review-card">
                    <p className="review-text">"HealthLink made it so easy for me to connect with a doctor online. I highly recommend it to anyone!"</p>
                    <h3>John Doe</h3>
                    <p className="review-role">Software Engineer</p>
                </div>
                <div className="review-card">
                    <p className="review-text">"The telemedicine feature saved me a trip to the clinic. I love how convenient it is."</p>
                    <h3>Jane Smith</h3>
                    <p className="review-role">Teacher</p>
                </div>
                <div className="review-card">
                    <p className="review-text">"As a busy professional, being able to book appointments online is a game changer for me."</p>
                    <h3>Emily Johnson</h3>
                    <p className="review-role">Marketing Manager</p>
                </div>
            </div>
        </section>
    );
};

export default Reviews;
