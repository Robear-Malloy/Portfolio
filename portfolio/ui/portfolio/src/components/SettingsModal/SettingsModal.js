import React, { useContext, useState } from 'react';
import { ThemeContext } from '../../utils/ThemeContext';
import './SettingsModal.css';

const SettingsModal = ({ isOpen, onClose }) => {
  const { darkMode, toggleDarkMode } = useContext(ThemeContext);
  const [selectedLanguage, setSelectedLanguage] = useState('EN');

  const handleLanguageChange = (event) => {
    setSelectedLanguage(event.target.value);
  };

  if (!isOpen) return null;

  return (
    <div className={`settings-modal ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <div className="settings-modal-content">
        <button className="close-button" onClick={onClose} aria-label="Close Settings Modal">
          &times;
        </button>
        <h2>Settings</h2>

        <div className="settings-group">
          <label htmlFor="language-select">Language</label>
          <select
            id="language-select"
            value={selectedLanguage}
            onChange={handleLanguageChange}
            className="settings-select"
          >
            <option value="EN">English</option>
            <option value="FR">French</option>
            <option value="JP">Japanese</option>
            <option value="NL">Dutch</option>
          </select>
        </div>

        <div className="settings-group">
          <label htmlFor="dark-mode-switch">Dark Mode</label>
          <div className="slider-container">
            <input
              type="checkbox"
              id="dark-mode-switch"
              checked={darkMode}
              onChange={toggleDarkMode}
              className="slider-checkbox"
            />
            <label htmlFor="dark-mode-switch" className="slider"></label>
          </div>
        </div>

        <button className="save-button" onClick={onClose}>
          Save Changes
        </button>
      </div>
    </div>
  );
};

export default SettingsModal;
