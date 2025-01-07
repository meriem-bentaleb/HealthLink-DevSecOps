import React from "react";

const DashPatient = ({ patient, onEdit }) => {
  return (
    <div className="dash-patient">
      <h2>Patient Dashboard</h2>
      <p><strong>Name:</strong> {patient.firstName} {patient.lastName}</p>
      <p><strong>Email:</strong> {patient.email}</p>
      <p><strong>Phone:</strong> {patient.phone}</p>
      <p><strong>Address:</strong> {patient.address}</p>
      <p><strong>City:</strong> {patient.city}</p>
      <p><strong>Country:</strong> {patient.country}</p>
      <button onClick={onEdit}>Edit Profile</button>
    </div>
  );
};

export default DashPatient;
