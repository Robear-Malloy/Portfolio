import { useContext, useState } from 'react';
import { ThemeContext } from '../../utils/ThemeContext';
import { useTranslation } from 'react-i18next';
import './SettingsModal.css';

const SettingsModal = ({ isOpen, onClose }) => {
  const { t } = useTranslation();
  const { darkMode, toggleDarkMode } = useContext(ThemeContext);
  const { i18n } = useTranslation(); 
  const [selectedLanguage, setSelectedLanguage] = useState(i18n.language.toUpperCase());

  const handleLanguageChange = (event) => {
    const newLanguage = event.target.value.toLowerCase(); 
    setSelectedLanguage(event.target.value);
    i18n.changeLanguage(newLanguage);
  };

  if (!isOpen) return null;

  return (
    <div className={`settings-modal ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <div className="settings-modal-content">
        <button className="close-button" onClick={onClose} aria-label="Close Settings Modal">
          &times;
        </button>
        <h2>{t('settings.title')}</h2>

        <div className="settings-group">
          <label htmlFor="language-select">{t('settings.language')}</label>
          <select
            id="language-select"
            value={selectedLanguage}
            onChange={handleLanguageChange}
            className="settings-select"
          >
            <option value="EN">English</option>
            <option value="FR">Français</option>
            {/* <option value="JP">Japanese</option> */}
            {/* <option value="NL">Dutch</option> */}
          </select>
        </div>

        <div className="settings-group">
          <label htmlFor="dark-mode-switch">{t('settings.darkMode')}</label>
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
        {t('settings.save')}
        </button>
      </div>
    </div>
  );
};

export default SettingsModal;
