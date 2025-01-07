import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import PatientInfo from '../Components/PatientInfo';
import MedicalRecords from '../Components/MedicalRecords';
import Appointments from '../Components/Appointments';
import PatientSidebar from '../Components/PatientSidebar';
import '../Styles/PatientDashboard.css'; // Import CSS for styling

const PatientDashboard = () => {
    const { id } = useParams();
    const [patient, setPatient] = useState(null);
    const [medicalRecords, setMedicalRecords] = useState([]);
    const [appointments, setAppointments] = useState([]);
    const [activeSection, setActiveSection] = useState('patientInfo'); // Default to 'patientInfo' section
    const [loading, setLoading] = useState(true); // Loading state to show while data is being fetched

    // Fetch data on component mount
    useEffect(() => {
        const fetchPatientInfo = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/patients/${id}`);
                setPatient(response.data);
            } catch (error) {
                console.error('Error fetching patient data:', error);
            }
        };

        const fetchMedicalRecords = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/medical-records/patient/${id}`); // Correct patient ID
                setMedicalRecords(response.data);
            } catch (error) {
                console.error('Error fetching medical records:', error);
            }
        };

        const fetchAppointments = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/appointments/patient/${id}`); // Correct patient ID
                setAppointments(response.data);
            } catch (error) {
                console.error('Error fetching appointments:', error);
            }
        };

       // fetchPatientInfo();
        // fetchMedicalRecords();
       // fetchAppointments();
       // setLoading(false); // Set loading to false once data is fetched
        const fetchData = async () => {
            setLoading(true);
            try {
                await Promise.all([fetchPatientInfo(), fetchMedicalRecords(), fetchAppointments()]);
            } catch (error) {
                console.error('Error fetching data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();

    }, [id]);

    // If still loading, show a loading message
    if (loading) {
        return <div className="loading">Loading...</div>;
    }

    // Handle updating patient info
    const handleUpdatePatientInfo = async (updatedPatient) => {
        try {
            const response = await axios.put(`http://localhost:8080/api/patients/${updatedPatient.id}`, updatedPatient, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.status === 200) {
                setPatient(response.data); // Update the local patient data with the updated data
            }
        } catch (error) {
            console.error('Error updating patient info:', error);
        }
    };

    // Render the selected section based on activeSection
    const renderSection = () => {
        switch (activeSection) {
            case 'patientInfo':
                return <PatientInfo patient={patient} onUpdatePatientInfo={handleUpdatePatientInfo} />;
            case 'medicalRecords':
                return <MedicalRecords records={medicalRecords} />;
            case 'appointments':
                return <Appointments appointments={appointments} />;
            default:
                return <PatientInfo patient={patient} onUpdatePatientInfo={handleUpdatePatientInfo} />;
        }
    };

    return (
        <div className="patient-dashboard">
            {/* Sidebar on the left */}
            <div className="sidebar-container">
                <PatientSidebar setActiveSection={setActiveSection} />
            </div>

            {/* Main content area */}
            <div className="main-content">
                {renderSection()}
            </div>
        </div>
    );
};

export default PatientDashboard;
