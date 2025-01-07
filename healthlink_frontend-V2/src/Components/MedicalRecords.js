import React from 'react';
import '../Styles/MedicalRecords.css';

const MedicalRecords = ({ records }) => {
    return (
        <div className="medical-records">
            <h2>Medical Records</h2>
            <ul>
                {records.map((record) => (
                    <li key={record.id}>
                        <p><strong>Record Date: </strong>{record.recordDate}</p>
                        <p><strong>Doctor: </strong>{record.doctorName}</p>
                        <p><strong>Details: </strong>{record.details}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MedicalRecords;
