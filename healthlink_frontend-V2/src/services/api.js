import axios from "axios";

const API_URL = "http://localhost:8080";

// Fetch patient profile by ID
export const getPatientProfile = async (patientId) => {
  try {
    const response = await axios.get(`${API_URL}/patients/${patientId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching patient profile:", error);
    throw error;
  }
};

// Update patient profile
export const updatePatientProfile = async (patientId, updatedData) => {
  try {
    const response = await axios.put(`${API_URL}/patients/${patientId}`, updatedData);
    return response.data;
  } catch (error) {
    console.error("Error updating patient profile:", error);
    throw error;
  }
};
