import React, { useState } from "react";
import Filters from "../Components/Filters";
import DoctorCard from "../Components/DoctorCard";
import "../Styles/AppointmentPage.css";
import doctorData from "../mock/doctorData"; // Mocked data

const AppointmentPage = () => {
  const [filters, setFilters] = useState({});
  const [filteredDoctors, setFilteredDoctors] = useState(doctorData);

  const handleFilterChange = (key, value) => {
    const updatedFilters = { ...filters, [key]: value };
    setFilters(updatedFilters);

    const filtered = doctorData.filter((doctor) => {
      const matchesCity = updatedFilters.city
        ? doctor.city === updatedFilters.city
        : true;
      const matchesSpecialty = updatedFilters.specialty
        ? doctor.specialty === updatedFilters.specialty
        : true;
      return matchesCity && matchesSpecialty;
    });

    setFilteredDoctors(filtered);
  };

  return (
    <div className="appointment-page">
      <div className="top-filters">
        <Filters onFilterChange={handleFilterChange} />
      </div>
      <div className="doctor-list">
        {filteredDoctors.map((doctor) => (
          <DoctorCard key={doctor.id} doctor={doctor} />
        ))}
      </div>
    </div>
  );
};

export default AppointmentPage;
