import React, { useContext, useState } from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog } from '@fortawesome/free-solid-svg-icons';
import { ThemeContext } from '../../utils/ThemeContext';
import './Navbar.css';

const Navbar = () => {
  const { darkMode, toggleDarkMode } = useContext(ThemeContext);
  const [menuOpen, setMenuOpen] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedLanguage, setSelectedLanguage] = useState('EN');

  const toggleMenu = () => setMenuOpen(!menuOpen);
  const toggleModal = () => setModalOpen(!modalOpen);

  const handleLanguageChange = (event) => {
    setSelectedLanguage(event.target.value);
  };

  return (
    <nav className="navbar">
      <div className="hamburger-menu" onClick={toggleMenu}>
        <span className="bar"></span>
        <span className="bar"></span>
        <span className="bar"></span>
      </div>

      {menuOpen && (
        <div className="dropdown-menu">
          <Link to="/">Home</Link>
          <Link to="/experiences">Experiences</Link>
          <Link to="/video">Videos</Link>
          <Link to="/tutorial">Tutorials</Link>
        </div>
      )}

      <ul className="nav-center">
        <li><a href="#skills">Skills</a></li>
        <li><a href="#education">Education</a></li>
        <li><a href="#projects">Projects</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>

      <div className="settings-icon" onClick={toggleModal}>
        <FontAwesomeIcon icon={faCog} />
      </div>

      {modalOpen && (
        <div className="modal">
          <div className="modal-content">
            <h2>Settings</h2>

            {/* Language Dropdown */}
            <div className="modal-option">
              <label htmlFor="language-select">Select Language:</label>
              <select
                id="language-select"
                value={selectedLanguage}
                onChange={handleLanguageChange}
              >
                <option value="EN">EN</option>
                <option value="FR">FR</option>
                <option value="JP">JP</option>
                <option value="NL">NL</option>
              </select>
            </div>

            {/* Dark Mode Switch */}
            <div className="modal-option">
              <label htmlFor="dark-mode-switch">Dark Mode:</label>
              <input
                type="checkbox"
                id="dark-mode-switch"
                checked={darkMode}
                onChange={toggleDarkMode}
              />
            </div>

            {/* Close Button */}
            <button onClick={toggleModal}>Close</button>
          </div>
        </div>
      )}
    </nav>
  );
};

export default Navbar;
