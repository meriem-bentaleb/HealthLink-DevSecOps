import React from 'react';
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import '../Styles/Home.css'
import Hero from "../Components/Hero";
import Services from '../Components/Services';
import ExpertDoctors from '../Components/ExpertDoctors'
import Appointment from '../Components/Appointment';
import Reviews from "../Components/Reviews";
import About from '../Components/About';
const Home = () => {
    return (
        <div>

           <Hero />
           <About />
           <Services/>
           <Appointment/>
           <ExpertDoctors/>
           <Reviews />

       </div>
    );
}

export default Home;
