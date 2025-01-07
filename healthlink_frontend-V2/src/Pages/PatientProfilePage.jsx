import React, { useState } from "react";
import DashPatient from "../Components/DashPatient";
import ProfileEditForm from "../Components/ProfileEditForm";
import "../Styles/DashPatient.css";

const PatientProfilePage = () => {
  // Mock data for the patient profile
  const mockPatient = {
    id: 1,
    firstName: "John",
    lastName: "Doe",
    email: "john.doe@example.com",
    phone: "1234567890",
    address: "123 Main Street",
    city: "Casablanca",
    country: "Morocco",
  };

  const [patient, setPatient] = useState(mockPatient);
  const [isEditing, setIsEditing] = useState(false);

  const handleEdit = () => setIsEditing(true);

  const handleSave = (updatedData) => {
    setPatient(updatedData); // Update the patient data locally
    setIsEditing(false);
  };

  const handleCancel = () => setIsEditing(false);

  return (
    <div className="patient-profile-page">
      {isEditing ? (
        <ProfileEditForm
          patient={patient}
          onSave={handleSave}
          onCancel={handleCancel}
        />
      ) : (
        <DashPatient patient={patient} onEdit={handleEdit} />
      )}
    </div>
  );
};

export default PatientProfilePage;
