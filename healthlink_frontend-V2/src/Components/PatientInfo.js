import React, { useState } from 'react';
import '../Styles/PatientInfo.css'; // Updated for the new style

const PatientInfo = ({ patient, onUpdatePatientInfo }) => {
    const [editablePatient, setEditablePatient] = useState({ ...patient });
    const [isEditing, setIsEditing] = useState(false);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditablePatient({
            ...editablePatient,
            [name]: value
        });
    };

    const handleSave = () => {
        // Simple validation: Check if required fields are filled
        if (!editablePatient.firstName || !editablePatient.lastName || !editablePatient.email || !editablePatient.phone) {
            alert('Please fill in all required fields');
            return;
        }

        // If all required fields are valid, call the parent function to update patient info
        onUpdatePatientInfo(editablePatient);
        setIsEditing(false); // Exit edit mode after saving
    };

    const handleCancel = () => {
        setEditablePatient({ ...patient }); // Revert back to original patient data
        setIsEditing(false); // Exit edit mode without saving
    };

    if (!patient) {
        return <div>Loading patient info...</div>;
    }

    return (
        <div className="patient-info">
            <h2>Patient Information</h2>

            <div className="patient-details">
                <div className="patient-field">
                    <label>First Name:</label>
                    {isEditing ? (
                        <input
                            type="text"
                            name="firstName"
                            value={editablePatient.firstName}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.firstName}</p>
                    )}
                </div>

                <div className="patient-field">
                    <label>Last Name:</label>
                    {isEditing ? (
                        <input
                            type="text"
                            name="lastName"
                            value={editablePatient.lastName}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.lastName}</p>
                    )}
                </div>

                <div className="patient-field">
                    <label>Email:</label>
                    {isEditing ? (
                        <input
                            type="email"
                            name="email"
                            value={editablePatient.email}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.email}</p>
                    )}
                </div>

                <div className="patient-field">
                    <label>Phone:</label>
                    {isEditing ? (
                        <input
                            type="text"
                            name="phone"
                            value={editablePatient.phone}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.phone}</p>
                    )}
                </div>

                <div className="patient-field">
                    <label>Address:</label>
                    {isEditing ? (
                        <input
                            type="text"
                            name="address"
                            value={editablePatient.address}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.address}</p>
                    )}
                </div>

                <div className="patient-field">
                    <label>City:</label>
                    {isEditing ? (
                        <input
                            type="text"
                            name="city"
                            value={editablePatient.city || ''}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.city || 'N/A'}</p>
                    )}
                </div>

                <div className="patient-field">
                    <label>Country:</label>
                    {isEditing ? (
                        <input
                            type="text"
                            name="country"
                            value={editablePatient.country || ''}
                            onChange={handleInputChange}
                        />
                    ) : (
                        <p>{patient.country || 'N/A'}</p>
                    )}
                </div>
            </div>

            <div className="patient-actions">
                {isEditing ? (
                    <>
                        <button className="btn btn-primary" onClick={handleSave}>Save</button>
                        <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
                    </>
                ) : (
                    <button className="btn btn-edit" onClick={() => setIsEditing(true)}>Edit</button>
                )}
            </div>
        </div>
    );
};

export default PatientInfo;
