import React, { useState } from "react";
import "../Styles/DoctorCard.css";

const DoctorCard = ({ doctor }) => {
  const [currentDayIndex, setCurrentDayIndex] = useState(0); // First day of visible range
  const [visibleSlots, setVisibleSlots] = useState(3); // Initial visible slots

  // Generate days dynamically starting from today
  const today = new Date();
  const days = Array.from({ length: 30 }, (_, index) => {
    const date = new Date();
    date.setDate(today.getDate() + index);
    return {
      date: date.toISOString().split("T")[0], // Format: YYYY-MM-DD
      label: date.toLocaleDateString("en-US", {
        weekday: "long",
        day: "numeric",
        month: "short",
      }), // Example: Monday, Nov 20
    };
  });

  // Get the current range of days to display
  const visibleDays = days.slice(currentDayIndex, currentDayIndex + 3);

  const handleNextDays = () => {
    if (currentDayIndex + 3 < days.length) {
      setCurrentDayIndex(currentDayIndex + 3);
      setVisibleSlots(3); // Reset visible slots
    }
  };

  const handlePreviousDays = () => {
    if (currentDayIndex - 3 >= 0) {
      setCurrentDayIndex(currentDayIndex - 3);
      setVisibleSlots(3); // Reset visible slots
    }
  };

  const handleShowMoreSlots = () => {
    setVisibleSlots(visibleSlots + 3); // Show 3 more slots
  };

  return (
    <div className="doctor-card">
      {/* Doctor Info */}
      <div className="doctor-profile">
        <img
          src="https://via.placeholder.com/100" // Replace with actual doctor image
          alt="Doctor"
          className="doctor-image"
        />
        <div className="doctor-info">
          <h3>{doctor.name}</h3>
          <p>{doctor.specialty}</p>
          <p>{doctor.city}</p>
          <button className="view-profile">View Profile</button>
        </div>
      </div>

      {/* Availability Section */}
      <div className="availability">
        {/* Day Navigation */}
        <div className="day-navigation">
          <button onClick={handlePreviousDays} disabled={currentDayIndex === 0}>
            &lt;
          </button>
          {visibleDays.map((day) => (
            <div className="day-info" key={day.date}>
              <h4>{day.label}</h4>
            </div>
          ))}
          <button
            onClick={handleNextDays}
            disabled={currentDayIndex + 3 >= days.length}
          >
            &gt;
          </button>
        </div>

        {/* Slots */}
        <div className="slots">
          {visibleDays.map((day) => (
            <div className="day-slots" key={day.date}>
              {day.date === today.toISOString().split("T")[0] ? (
                <p className="no-slots">No slots available for today</p>
              ) : (
                doctor.availability.slice(0, visibleSlots).map((slot) => (
                  <button key={`${day.date}-${slot.id}`} className="slot">
                    {slot.time}
                  </button>
                ))
              )}
              {visibleSlots < doctor.availability.length && day.date !== today.toISOString().split("T")[0] && (
                <button onClick={handleShowMoreSlots} className="more-slots">
                  Show More...
                </button>
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default DoctorCard;
