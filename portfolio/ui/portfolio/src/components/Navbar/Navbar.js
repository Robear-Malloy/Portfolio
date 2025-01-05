import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCog } from '@fortawesome/free-solid-svg-icons';
import SettingsModal from '../SettingsModal/SettingsModal';
import './Navbar.css';

const Navbar = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const location = useLocation(); 

  const toggleMenu = () => setMenuOpen(!menuOpen);
  const toggleModal = () => setModalOpen(!modalOpen);

  const renderNavCenterLinks = () => {
    if (location.pathname === '/video') {
      return (
        <ul className="nav-center">
          <li><a href="#tutorials">Tutorials</a></li>
          <li><a href="#projects">Projects</a></li>
        </ul>
      );
    } else if (location.pathname === '/') {
      return (
        <ul className="nav-center">
          <li><a href="#skills">Skills</a></li>
          <li><a href="#experience">Experience</a></li>
          <li><a href="#projects">Projects</a></li>
          <li><a href="#about-me">About Me</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      );
    } else if (location.pathname === '/experiences') {
      return (
        <ul className="nav-center">
          <li><a href="#education">Education</a></li>
          <li><a href="#skills">Skills</a></li>
          <li><a href="#experience">Experience</a></li>
          <li><a href="#projects">Projects</a></li>
        </ul>
      );
    } else if (location.pathname === '/tutorial') {
      return (
        <ul className="nav-center">
          <li><a href="#tutorial">API Tutorial</a></li>
          <li><a href="#thank-you">Thank You</a></li>
        </ul>
      );
    }
    return (
      <ul className="nav-center">
        <li><a href="#home">Home</a></li>
      </ul>
    );
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <div className={`hamburger-menu ${menuOpen ? 'open' : ''}`} onClick={toggleMenu}>
          <FontAwesomeIcon icon={faBars} size="xl" />
        </div>
      </div>

      {menuOpen && (
        <div className="dropdown-menu">
          <Link to="/">Home</Link>
          <Link to="/experiences">Experiences</Link>
          <Link to="/video">Videos</Link>
          <Link to="/tutorial">API Tutorial</Link>
        </div>
      )}

      {renderNavCenterLinks()}

      <div className="settings-icon" onClick={toggleModal}>
        <FontAwesomeIcon icon={faCog} />
      </div>

      <SettingsModal isOpen={modalOpen} onClose={toggleModal} />
    </nav>
  );
};

export default Navbar;
