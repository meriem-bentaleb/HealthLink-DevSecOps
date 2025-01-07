import React from 'react';
import { useLocation } from 'react-router-dom';
import Navbar from './Navbar';
import Footer from './Footer';

const Layout = ({ children }) => {
    const location = useLocation();

    // Define paths where Navbar and Footer should NOT be shown
    const excludePaths = ['/Login', '/DocSignup'];

    // Check if the current path is in the exclude list
    const isExcluded = excludePaths.includes(location.pathname) || location.pathname.startsWith('/dashboard/');

    return (
        <div>
            {/* Conditionally render Navbar */}
            {!isExcluded && <Navbar />}

            <main>{children}</main>

            {/* Conditionally render Footer */}
            {!isExcluded && <Footer />}
        </div>
    );
};

export default Layout;

