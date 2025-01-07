import React from "react"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import "./App.css";
import Layout from "./Components/Layout";
import Home from "./Pages/Home";
import NotFound from "./Pages/NotFound";
import Login from "./Pages/Login";
import DocSignup from "./Pages/DocSignup";
import PatientSignup from "./Pages/PatientSignup";
import PatientProfilePage from "./Pages/PatientProfilePage";
import AppointmentPage from "./Pages/AppointmentPage";
import DashboardPatient from "./Pages/DashboardPatient";

function App() {
  return (

      <Router>
        <Layout>
        <div>
          <Routes>

            <Route path="/" element={<Home />} />
            <Route path="*" element={<NotFound />} />
              <Route path="/Login" element={<Login />} />
              <Route path="/DocSignup" element={<DocSignup />} />
              <Route path="/PatientSignup" element={<PatientSignup />} />
              <Route path="/appointments" element={<AppointmentPage />} />
              <Route path="/profile" element={<PatientProfilePage />} />
              <Route path="/dashboard/:id" element={<DashboardPatient />} />

          </Routes>
        </div>
        </Layout>
      </Router>
  )
}

export default App;
