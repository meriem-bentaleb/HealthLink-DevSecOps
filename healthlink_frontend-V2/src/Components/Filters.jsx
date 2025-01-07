import React from "react";
import "../Styles/Filters.css";

const Filters = ({ onFilterChange }) => {
  const handleFilterChange = (event) => {
    const { name, value } = event.target;
    onFilterChange(name, value);
  };

  return (
    <div className="filters">
      <select name="city" onChange={handleFilterChange}>
        <option value="">Select City</option>
        <option value="Casablanca">Casablanca</option>
        <option value="Rabat">Rabat</option>
        <option value="Marrakech">Marrakech</option>
        <option value="Tangier">Tangier</option>
        <option value="Fes">Fes</option>
        <option value="Agadir">Agadir</option>
        <option value="Meknes">Meknes</option>
        <option value="Oujda">Oujda</option>
      </select>
      <select name="specialty" onChange={handleFilterChange}>
        <option value="">Select Specialty</option>
        <option value="Pediatric Nephrologist">Pediatric Nephrologist</option>
        <option value="Orthopedic Surgeon">Orthopedic Surgeon</option>
        <option value="Cardiologist">Cardiologist</option>
        <option value="Dermatologist">Dermatologist</option>
        <option value="Neurologist">Neurologist</option>
        <option value="Oncologist">Oncologist</option>
        <option value="Psychiatrist">Psychiatrist</option>
        <option value="General Practitioner">General Practitioner</option>
      </select>
      <select name="appointmentType" onChange={handleFilterChange}>
        <option value="">Select Appointment Type</option>
        <option value="In-Clinic">In-Clinic</option>
        <option value="Video Call">Video Call</option>
      </select>
    </div>
  );
};

export default Filters;
